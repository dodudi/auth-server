CREATE TABLE IF NOT EXISTS role_info
(
    role_id          SERIAL PRIMARY KEY,
    role_name VARCHAR(255) UNIQUE,
    create_date_time TIMESTAMP(6),
    update_date_time TIMESTAMP(6)
);

CREATE TABLE IF NOT EXISTS user_info
(
    user_id          SERIAL PRIMARY KEY,
    password         VARCHAR(255),
    username         VARCHAR(255),
    create_date_time TIMESTAMP(6),
    update_date_time TIMESTAMP(6)
);

CREATE TABLE IF NOT EXISTS user_role
(
    id               SERIAL PRIMARY KEY,
    role_id          INTEGER,
    user_id          INTEGER,
    create_date_time TIMESTAMP(6),
    update_date_time TIMESTAMP(6),
    CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES role_info (role_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS client_info
(
    id SERIAL PRIMARY KEY,

);