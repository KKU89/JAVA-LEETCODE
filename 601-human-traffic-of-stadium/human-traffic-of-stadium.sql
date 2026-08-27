# Write your MySQL query statement below
WITH qualified AS (
    SELECT id, visit_date, people,
           id - ROW_NUMBER() OVER (ORDER BY id) AS grp
    FROM Stadium
    WHERE people >= 100
),
valid_groups AS (
    SELECT grp
    FROM qualified
    GROUP BY grp
    HAVING COUNT(*) >= 3
)
SELECT id, visit_date, people
FROM qualified
WHERE grp IN (SELECT grp FROM valid_groups)
ORDER BY visit_date;