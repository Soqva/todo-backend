# Tusur-todo-app

Tusur-todo-app is an application for managing tasks that need to be completed by a certain date. Communication between clients and the server is done according to the REST API.

This application is developed as a university educational project. The project consists of two parts: frontend side and backend side. Here is the backend side of the project. If you want to see the frontend side you can follow the link: [Tusur-todo-frontend](https://github.com/matyexd/todo-frontend).

Tools used (backend):
- Docker
- Flyway
- Java 11
- Lombok
- MapStruct
- Maven
- PostgreSQL
- Spring Boot (JPA, Validation, Web)

## Key Features

- Registration / authorization new users
- Creation / getting / patching / deleting categories, which contain a list of user's tasks
- Creation / getting / patching / deleting tasks

## Usage (API)

The backend of the application is deployed on the Heroku remote server.

Base_URL (with context path of the project): `https://tusur-todo-backend.herokuapp.com/api/v1`

### 1. Registration:

URL: `Base_URL/auth/sign-up`

#### 1.1 Sign-up (create new user)

Request HTTP method: **_POST_**<br />
Request HTTP body:

```json
{
    "email": "test@yandex.ru",
    "password": "test"
}
```

Response if success:

HTTP status: 201 (CREATED)<br />
HTTP headers: location (URL) with saved user's id<br />
HTTP body: none

Response if fail:

HTTP status: 409 (CONFLICT)<br />
HTTP body:

```json
{
    "errors": {
        "entityAlreadyExist": "user with email = test@yandex.ru already exist"
    }
}
```

### 2. Authorization

URL: `Base_URL/auth/sign-in`

#### 2.1 Sign-in

Request HTTP method: **_POST_**<br />
Request HTTP body:

```json
{
    "email": "test@yandex.ru",
    "password": "test"
}
```

Response if success:

HTTP status: 200 (OK)<br />
HTTP body:

```json
{
    "id": 1,
    "email": "test@yandex.ru",
    "username": "default user",
    "categories": []
}
```

Response if fail:

HTTP status: 409 (CONFLICT)<br />
HTTP body:

```json
{
    "errors": {
        "incorrectEmailOrPassword": "email or password are incorrect"
    }
}
```

### 3. Users

URL: `Base_URL/users`

#### 3.1 Get all existing users

Request HTTP method: **_GET_**<br />
Request HTTP body: none

Response HTTP status: 200 (OK)<br />
Response HTTP body:

```json
[
    {
        "id": 1,
        "email": "test@yandex.ru",
        "username": "default user",
        "categories": [...] // may has some categories with their tasks here
    },
    {
        "id": 2,
        "email": "test2@yandex.com",
        "username": "test2",
        "categories": [...]
    },
    {
        "id": 3,
        "email": "test3@gmail.com",
        "username": "default user",
        "categories": []
    }
]
```

URL: `Base_URL/users/{id}`

#### 3.2 Get an existing user by their id

Request HTTP method: **_GET_**<br />
Request HTTP body: none

Response if success:

HTTP status: 200 (OK)<br />
HTTP body:

```json
{
    "id": 1,
    "email": "test@yandex.ru",
    "username": "default user",
    "categories": []
}
```

Response if fail:

HTTP status: 404 (NOT FOUND)<br />
HTTP body:

```json
{
    "errors": {
        "noSuchEntity": "there is no user with id = -1"
    }
}
```

#### 3.3 Patch an existing user by their id

Request HTTP method: **_PATCH_**<br />
Request HTTP body:

```json
{
    "username": "newTestName",
    "email": "test@yandex.com",
    "password": "newTestPassword"
}
```

Response if success:

HTTP status: 200 (OK)<br />
HTTP body:

```json
{
    "id": 1,
    "email": "test@yandex.com",
    "username": "newTestName",
    "categories": []
}
```

Response if fail:

HTTP status: 404 (NOT FOUND)<br />
HTTP body:

```json
{
    "errors": {
        "noSuchEntity": "there is no user with id = -1"
    }
}
```

### 4. Categories

URL: `Base_URL/categories`

#### 4.1 Create new category

Request HTTP method: **_POST_**<br />
Request HTTP body:

```json
{
    "title": "Test category",
    "userId": {
        "id": 1
    }
}
```

HTTP status: 201 (CREATED)<br />
HTTP headers: location (URL) with saved category's id<br />
HTTP body: none

#### 4.2 Get all existing categories

Request HTTP method: **_GET_**<br />
Request HTTP body: none

Response HTTP status: 200 (OK)<br />
Response HTTP body:

```json
[
    {
        "id": 1,
        "title": "The first category",
        "userId": {
            "id": 1
        },
        "tasks": [...] // may has some tasks
    },
    {
        "id": 2,
        "title": "The second category",
        "userId": {
            "id": 2
        },
        "tasks": []
    },
    {
        "id": 3,
        "title": "The third category",
        "userId": {
            "id": 3
        },
        "tasks": []
    }
]
```

URL: `Base_URL/users/{id}`

#### 4.2 Get an existing category by its id

Request HTTP method: **_GET_**<br />
Request HTTP body: none

Response if success:

HTTP status: 200 (OK)<br />
HTTP body:

```json
{
    "id": 1,
    "title": "The first category",
    "userId": {
        "id": 1
    },
    "tasks": []
}
```

Response if fail:

HTTP status: 404 (NOT FOUND)<br />
HTTP body:

```json
{
    "errors": {
        "noSuchEntity": "there is no category with id = -1"
    }
}
```

#### 4.3 Patch an existing category by its id

Request HTTP method: **_PATCH_**<br />
Request HTTP body:

```json
{
    "title": "new first category title"
}
```

Response if success:

HTTP status: 200 (OK)<br />
HTTP body:

```json
{
    "id": 1,
    "title": "new first category title",
    "userId": {
        "id": 1
    },
    "tasks": []
}
```

Response if fail:

HTTP status: 404 (NOT FOUND)<br />
HTTP body:

```json
{
    "errors": {
        "noSuchEntity": "there is no category with id = -1"
    }
}
```

#### 4.4 Delete an existing category by its id

Request HTTP method: **_DELETE_**<br />
Request HTTP body: none

Response if success:

HTTP status: 200 (OK)<br />
HTTP body: none

Response if fail:

HTTP status: 404 (NOT FOUND)<br />
HTTP body:

```json
{
    "errors": {
        "noSuchEntity": "there is no category with id = -1"
    }
}
```

### 5. Tasks

URL: `Base_URL/tasks`

#### 5.1 Create new task

Request HTTP method: **_POST_**<br />
Request HTTP body:

```json
{
    "endDate": "2022-05-23",
    "title": "test task",
     "description": "here is a description of the test task",
     "status": "CREATED",
     "categoryId": {
         "id": 1
     }
}
```

HTTP status: 201 (CREATED)<br />
HTTP headers: location (URL) with saved task's id<br />
HTTP body: none

#### 5.2 Get all existing tasks

Request HTTP method: **_GET_**<br />
Request HTTP body: none

Response HTTP status: 200 (OK)<br />
Response HTTP body:

```json
[
    {
        "id": 1,
        "endDate": "2022-05-23",
        "title": "test task",
        "description": "here is a description of the test task",
        "status": "CREATED",
        "categoryId": {
            "id": 1
        }
    },
    {
        "id": 2,
        "endDate": "2022-05-20",
        "title": "The second task",
        "description": null,
        "status": "CREATED",
        "categoryId": {
            "id": 1
        }
    },
    {
        "id": 3,
        "endDate": "2022-05-20",
        "title": "The third task",
        "description": "The third task`s description",
        "status": "CREATED",
        "categoryId": {
            "id": 2
        }
    }
]
```

URL: `Base_URL/users/{id}`

#### 5.3 Get an existing task by its id

Request HTTP method: **_GET_**<br />
Request HTTP body: none

Response if success:

HTTP status: 200 (OK)<br />
HTTP body:

```json
{
    "id": 1,
    "endDate": "2022-05-23",
    "title": "test task",
    "description": "here is a description of the test task",
    "status": "CREATED",
    "categoryId": {
        "id": 1
    }
}
```

Response if fail:

HTTP status: 404 (NOT FOUND)<br />
HTTP body:

```json
{
    "errors": {
        "noSuchEntity": "there is no task with id = -1"
    }
}
```

#### 5.4 Patch an existing task by its id

Request HTTP method: **_PATCH_**<br />
Request HTTP body:

```json
{
    "endDate": "2022-05-25",
    "title": "New task title",
    "description": "New description"
}
```

Response if success:

HTTP status: 200 (OK)<br />
HTTP body:

```json
{
    "id": 1,
     "endDate": "2022-05-25",
    "title": "New task title",
    "description": "New description",
    "status": "CREATED",
    "categoryId": {
        "id": 1
    }
}
```

Response if fail:

HTTP status: 404 (NOT FOUND)<br />
HTTP body:

```json
{
    "errors": {
        "noSuchEntity": "there is no task with id = -1"
    }
}
```

#### 5.5 Delete an existing task by its id

Request HTTP method: **_DELETE_**<br />
Request HTTP body: none

Response if success:

HTTP status: 200 (OK)<br />
HTTP body: none

Response if fail:

HTTP status: 404 (NOT FOUND)<br />
HTTP body:

```json
{
    "errors": {
        "noSuchEntity": "there is no task with id = -1"
    }
}
```
