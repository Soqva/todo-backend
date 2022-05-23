INSERT INTO category (title)
VALUES ('The first category'),
       ('The second category'),
       ('The third category');

INSERT INTO task (end_date, title, description, status, category_id)
VALUES (current_timestamp, 'The first task', 'The first task`s description', 'CREATED', 1),
       (current_timestamp, 'The second task', null, 'CREATED', 1),
       (current_timestamp, 'The third task', 'The third task`s description', 'CREATED', 2),
       (current_timestamp, 'The fourth task', 'The fourth task`s description', 'COMPLETED', 2),
       (current_timestamp, 'The fifth task', null, 'COMPLETED', 3);
