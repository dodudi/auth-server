MERGE INTO role_info AS r
    USING (SELECT 'ADMIN' AS role_name, NOW() AS create_date_time, NOW() AS update_date_time FROM dual
           UNION ALL
           SELECT 'USER', NOW(), NOW() FROM dual
           UNION ALL
           SELECT 'GUEST', NOW(), NOW() FROM dual) AS new_data
    ON r.role_name = new_data.role_name
    WHEN NOT MATCHED THEN
        INSERT (role_name, create_date_time, update_date_time)
            VALUES (new_data.role_name, new_data.create_date_time, new_data.update_date_time);