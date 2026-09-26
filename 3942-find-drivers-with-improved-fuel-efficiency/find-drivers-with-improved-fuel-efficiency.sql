WITH trip_efficiency AS (
    SELECT 
        driver_id,
        distance_km / fuel_consumed AS efficiency,
        CASE 
            WHEN MONTH(trip_date) BETWEEN 1 AND 6 THEN 'first'
            ELSE 'second'
        END AS half
    FROM trips
),
driver_halves AS (
    SELECT 
        driver_id,
        AVG(CASE WHEN half = 'first' THEN efficiency END) AS first_half_avg,
        AVG(CASE WHEN half = 'second' THEN efficiency END) AS second_half_avg
    FROM trip_efficiency
    GROUP BY driver_id
    HAVING first_half_avg IS NOT NULL 
       AND second_half_avg IS NOT NULL
       AND second_half_avg > first_half_avg
)
SELECT 
    d.driver_id,
    d.driver_name,
    ROUND(dh.first_half_avg, 2) AS first_half_avg,
    ROUND(dh.second_half_avg, 2) AS second_half_avg,
    ROUND(dh.second_half_avg - dh.first_half_avg, 2) AS efficiency_improvement
FROM driver_halves dh
JOIN drivers d ON d.driver_id = dh.driver_id
ORDER BY efficiency_improvement DESC, d.driver_name ASC;