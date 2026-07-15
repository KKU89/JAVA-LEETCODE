WITH DailyRevenue AS (
    SELECT 
        visited_on, 
        SUM(amount) AS daily_total
    FROM Customer
    GROUP BY visited_on
),
MovingStats AS (
    SELECT 
        visited_on,
        SUM(daily_total) OVER (
            ORDER BY visited_on 
            ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
        ) AS amount,
        ROUND(AVG(daily_total) OVER (
            ORDER BY visited_on 
            ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
        ), 2) AS average_amount
    FROM DailyRevenue
)

SELECT * FROM MovingStats
WHERE visited_on >= (
    SELECT DATE_ADD(MIN(visited_on), INTERVAL 6 DAY) 
    FROM Customer
)
ORDER BY visited_on;