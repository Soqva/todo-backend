CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(128) NOT NULL UNIQUE,
    password   VARCHAR(512) NOT NULL,
    email      VARCHAR(128) NOT NULL UNIQUE,
    first_name VARCHAR(64),
    last_name  VARCHAR(64)
);

CREATE TABLE category
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL
);

CREATE TABLE task_status
(
    id     SERIAL PRIMARY KEY,
    status VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE task
(
    id          BIGSERIAL PRIMARY KEY,
    end_date    TIMESTAMP    NOT NULL,
    title       VARCHAR(128) NOT NULL,
    description VARCHAR(2048),
    status_id   INT          NOT NULL REFERENCES task_status (id) ON DELETE SET NULL ON UPDATE CASCADE,
    category_id BIGINT       NOT NULL REFERENCES category (id) ON DELETE CASCADE ON UPDATE CASCADE
);
