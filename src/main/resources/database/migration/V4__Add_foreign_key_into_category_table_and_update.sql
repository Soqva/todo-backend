ALTER TABLE category
    ADD COLUMN user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE DEFAULT 1;

UPDATE category
SET user_id = 1
WHERE id = 1;

UPDATE category
SET user_id = 2
WHERE id = 2;

UPDATE category
SET user_id = 3
WHERE id = 3;
