# Write your MySQL query statement below
WITH valid_days AS (
    SELECT a.user_id, a.action_date, a.action
    FROM activity a
    JOIN (
        SELECT user_id, action_date
        FROM activity
        GROUP BY user_id, action_date
        HAVING COUNT(*) = 1
    ) b
      ON a.user_id = b.user_id
     AND a.action_date = b.action_date
),
numbered AS (
    SELECT user_id,
           action,
           action_date,
           ROW_NUMBER() OVER (PARTITION BY user_id, action ORDER BY action_date) AS rn
    FROM valid_days
),
streaks AS (
    SELECT user_id,
           action,
           MIN(action_date) AS start_date,
           MAX(action_date) AS end_date,
           COUNT(*) AS streak_length
    FROM numbered
    GROUP BY user_id, action, DATE_SUB(action_date, INTERVAL rn DAY)
    HAVING COUNT(*) >= 5
),
ranked AS (
    SELECT *,
           ROW_NUMBER() OVER (
               PARTITION BY user_id
               ORDER BY streak_length DESC, start_date ASC
           ) AS rk
    FROM streaks
)
SELECT user_id,
       action,
       streak_length,
       start_date,
       end_date
FROM ranked
WHERE rk = 1
ORDER BY streak_length DESC, user_id ASC;