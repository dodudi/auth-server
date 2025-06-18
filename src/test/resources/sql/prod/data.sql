INSERT INTO role_info (role_name, create_date_time, update_date_time )
VALUES ('ADMIN', NOW(), NOW()),
       ('USER', NOW(), NOW()),
       ('GUEST', NOW(), NOW())
ON CONFLICT (role_name) DO NOTHING;