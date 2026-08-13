# Write your MySQL query statement below
WITH RankedReviews AS (
    SELECT 
        employee_id,
        rating,
        review_date,
        ROW_NUMBER() OVER (PARTITION BY employee_id ORDER BY review_date DESC) as rn
    FROM performance_reviews
),
LastThree AS (
    SELECT 
        employee_id,
        MAX(CASE WHEN rn = 1 THEN rating END) AS r1,
        MAX(CASE WHEN rn = 2 THEN rating END) AS r2,
        MAX(CASE WHEN rn = 3 THEN rating END) AS r3
    FROM RankedReviews
    WHERE rn <= 3
    GROUP BY employee_id
    HAVING COUNT(*) = 3
)
SELECT 
    e.employee_id,
    e.name,
    (lt.r1 - lt.r3) AS improvement_score
FROM LastThree lt
JOIN employees e ON lt.employee_id = e.employee_id
WHERE lt.r3 < lt.r2 AND lt.r2 < lt.r1
ORDER BY improvement_score DESC, e.name ASC;