INSERT INTO role_info (create_date_time, update_date_time, role_name)
VALUES (NOW(), NOW(), 'ADMIN'),
       (NOW(), NOW(), 'USER'),
       (NOW(), NOW(), 'GUEST')
ON CONFLICT (role_name) DO NOTHING;