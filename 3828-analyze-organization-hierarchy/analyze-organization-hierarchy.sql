# Write your MySQL query statement below
WITH RECURSIVE levels AS (
SELECT employee_id, employee_name, manager_id, salary, 1 AS level
FROM Employees
WHERE manager_id IS NULL
UNION ALL
SELECT e.employee_id, e.employee_name, e.manager_id, e.salary, l.level + 1
FROM Employees e
JOIN levels l ON e.manager_id = l.employee_id
),
paths AS (
SELECT employee_id AS emp_id, employee_id AS ancestor_id, salary
FROM Employees
UNION ALL
SELECT p.emp_id, e.manager_id, p.salary
FROM paths p
JOIN Employees e ON p.ancestor_id = e.employee_id
WHERE e.manager_id IS NOT NULL
),
agg AS (
SELECT ancestor_id, COUNT(DISTINCT emp_id) - 1 AS team_size, SUM(salary) AS budget
FROM paths
GROUP BY ancestor_id
)
SELECT l.employee_id, l.employee_name, l.level, a.team_size, a.budget
FROM levels l
JOIN agg a ON l.employee_id = a.ancestor_id
ORDER BY l.level ASC, a.budget DESC, l.employee_name ASC;

