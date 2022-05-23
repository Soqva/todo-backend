CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    email    VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(512) NOT NULL,
    username VARCHAR(128) NOT NULL
);

CREATE TABLE category
(
    id    BIGSERIAL PRIMARY KEY,
    title VARCHAR(128) NOT NULL
);

CREATE TABLE task
(
    id          BIGSERIAL PRIMARY KEY,
    end_date    TIMESTAMP    NOT NULL,
    title       VARCHAR(128) NOT NULL,
    description VARCHAR(2048),
    status      VARCHAR(32)  NOT NULL,
    category_id BIGINT       NOT NULL REFERENCES category (id) ON DELETE CASCADE ON UPDATE CASCADE
);
