WITH RECURSIVE
ordered AS (
    SELECT student_id,
           subject,
           hours_studied,
           session_date,
           ROW_NUMBER() OVER (PARTITION BY student_id ORDER BY session_date) AS pos,
           LAG(session_date) OVER (PARTITION BY student_id ORDER BY session_date) AS prev_date
    FROM study_sessions
),
gaps AS (
    SELECT student_id,
           MAX(DATEDIFF(session_date, prev_date)) AS max_gap
    FROM ordered
    WHERE prev_date IS NOT NULL
    GROUP BY student_id
),
valid_students AS (
    SELECT student_id
    FROM ordered
    GROUP BY student_id
    HAVING COUNT(*) >= 6
),
valid_students2 AS (
    SELECT v.student_id
    FROM valid_students v
    LEFT JOIN gaps g ON v.student_id = g.student_id
    WHERE g.max_gap IS NULL OR g.max_gap <= 2
),
nums AS (
    SELECT 3 AS L
    UNION ALL
    SELECT L + 1
    FROM nums
    WHERE L + 1 <= 50
),
session_data AS (
    SELECT o.student_id,
           o.pos,
           o.subject,
           o.hours_studied,
           COUNT(*) OVER (PARTITION BY o.student_id) AS total_sessions
    FROM ordered o
    JOIN valid_students2 v ON o.student_id = v.student_id
),
pattern_check AS (
    SELECT sd.student_id,
           sd.total_sessions,
           n.L,
           SUM(sd.hours_studied) AS total_hours,
           COUNT(DISTINCT CASE WHEN sd.pos <= n.L THEN sd.subject END) AS distinct_first,
           SUM(CASE WHEN sd2.subject IS NOT NULL AND sd.subject != sd2.subject THEN 1 ELSE 0 END) AS mismatch,
           COUNT(DISTINCT sd.subject) AS distinct_total
    FROM session_data sd
    CROSS JOIN nums n
    LEFT JOIN session_data sd2
        ON sd2.student_id = sd.student_id
        AND sd2.pos = sd.pos + n.L
    WHERE n.L BETWEEN 3 AND sd.total_sessions / 2
      AND sd.total_sessions % n.L = 0
    GROUP BY sd.student_id, sd.total_sessions, n.L
    HAVING distinct_first = n.L
       AND mismatch = 0
       AND distinct_total = n.L
),
best_pattern AS (
    SELECT student_id,
           L AS cycle_length,
           total_hours,
           ROW_NUMBER() OVER (PARTITION BY student_id ORDER BY L DESC, total_hours DESC) AS rn
    FROM pattern_check
)
SELECT s.student_id,
       s.student_name,
       s.major,
       bp.cycle_length,
       bp.total_hours AS total_study_hours
FROM best_pattern bp
JOIN students s ON bp.student_id = s.student_id
WHERE bp.rn = 1
ORDER BY bp.cycle_length DESC, bp.total_hours DESC;