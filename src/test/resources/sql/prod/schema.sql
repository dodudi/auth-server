CREATE TABLE role_info
(
    role_id          SERIAL PRIMARY KEY,
    role_name        VARCHAR(255) UNIQUE,
    create_date_time TIMESTAMP(6),
    update_date_time TIMESTAMP(6)
);

CREATE TABLE user_info
(
    user_id          SERIAL PRIMARY KEY,
    password         VARCHAR(255) NOT NULL,
    username         VARCHAR(255) NOT NULL,
    create_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);

CREATE TABLE user_role
(
    id               SERIAL PRIMARY KEY,
    role_id          INTEGER NOT NULL,
    user_id          INTEGER NOT NULL,
    create_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT FK_role_id FOREIGN KEY (role_id) REFERENCES role_info (role_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES user_info (user_id) ON DELETE CASCADE
);


CREATE TABLE client_info
(
    id               SERIAL PRIMARY KEY,
    client_id        VARCHAR(255) UNIQUE NOT NULL,
    client_secret    VARCHAR(255)        NOT NULL,
    scope            VARCHAR(255),
    access_token     TEXT,
    refresh_token    TEXT,
    create_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);