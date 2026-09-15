# Write your MySQL query statement below
WITH WeeklyMeetingHours AS (
    SELECT 
        employee_id,
        YEARWEEK(meeting_date, 1) AS week_id,
        SUM(duration_hours) AS total_hours
    FROM meetings
    GROUP BY employee_id, YEARWEEK(meeting_date, 1)
    HAVING SUM(duration_hours) > 20
)
SELECT 
    e.employee_id,
    e.employee_name,
    e.department,
    COUNT(w.week_id) AS meeting_heavy_weeks
FROM employees e
JOIN WeeklyMeetingHours w ON e.employee_id = w.employee_id
GROUP BY e.employee_id, e.employee_name, e.department
HAVING COUNT(w.week_id) >= 2
ORDER BY meeting_heavy_weeks DESC, e.employee_name ASC;