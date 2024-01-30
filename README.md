# PARTIU

This project aims to provide an API for managing an event app.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate
- PostgreSQL

## Implemented Features 
-  The project is an event management application that involves several main classes: Event, Category, Address, Locale, and User. These classes have relationships with each other, where to create an event, author, category, location and address identifiers are needed.

-  Additionally, the project includes custom GET methods with filters to search by fee, city, state and category. Authentication and security are guaranteed by Spring Security and JWT (JSON Web Tokens), providing an additional layer of protection for endpoints and application resources.

-  In short, the project offers functionalities to create, view and manage events, ensuring a safe and efficient experience for users.



# API ENDPOINTS:

## User Controller

| Endpoint          | Method | Description                           |
|-------------------|--------|-------------------------------------|
| /api/user        | GET    | Returns all users.                   |
| /api/user/gender | GET    | Returns a specific user by gender.  |
| /api/user/:id    | GET    | Returns a specific user by id.      |
| /api/user/register | POST  | Registers a new user.                |
| /api/user/login  | POST   | User logs in.                        |
| /api/user/:id    | PUT    | Updates an existing user.            |
| /api/user/:id    | DELETE | Removes an existing user.            |

### Example Post methods

#### Register

```json
{
    "name" : "exemplo",
    "email" : "exemplo@gmail.com",
    "password" : "teste123",
    "avatarUrl" : "http://exemplo.url",
    "gender" : "MASCULINO"
}
```

#### Login
```json
{
    "email" : "exemplo@gmail.com",
    "password" : "teste123"
}
```

## Event Controller

| Endpoint          | Method | Description                           |
|-------------------|--------|-------------------------------------|
| /api/event        | GET    | Returns all paginated events.        |
| /api/event/:id    | GET    | Returns a specific event.            |
| /api/event/:fee   | GET    | Returns all paginated events by ticket fee.      |
| /api/event/:city  | GET    | Returns all paginated events by city.      |
| /api/event/:state | GET    | Returns all paginated events by state.      |
| /api/event/:fee   | GET    | Returns all paginated events by category name.      |
| /api/event        | POST   | Registers a new event.               |
| /api/event/:id    | PUT    | Updates an existing event.           |
| /api/event/:id    | DELETE | Removes an existing event.           |

### Example Post method

#### Create Event

```json
{
  "title": "exemplo",
  "description": "exemplo",
  "coverUrl": "http://example.com/cover.jp7g233",
  "startAt": "2024-02-18T10:00:00",
  "endsIn": "2024-02-18T22:00:00",
  "fee": 10000,
  "authorId": "id do autor",
  "localeId" : "id da localidade",
  "categoryId" : "id da categoria",
  "address" : {
    "street" : "exemplo",
    "neighborhood" : "exemplo",
    "zipCode" : "exemplo",
    "ref" : "exemplo"
  }
```

## Category Controller

| Endpoint          | Method | Description                           |
|-------------------|--------|-------------------------------------|
| /api/category     | GET    | Returns all categories.              |
| /api/category/:id | GET    | Returns a specific category by id.   |
| /api/category/:id | GET    | Returns a specific category by name. |
| /api/category     | POST   | Registers a new category.            |
| /api/category/:id | PUT    | Updates an existing category.        |
| /api/category/:id | DELETE | Removes an existing category.        |

### Example Post Method

#### Create category

```json
{
    "name" : "exemplo"
}
```

## Locale Controller

| Endpoint          | Method | Description                           |
|-------------------|--------|-------------------------------------|
| /api/locale       | GET    | Returns all locales.                 |
| /api/locale/:id   | GET    | Returns a specific locale by id.     |
| /api/locale       | POST   | Registers a new locale.              |
| /api/locale/:id  | PUT    | Updates an existing locale.          |
| /api/locale/:id  | DELETE | Removes an existing locale.          |

### Example Post Method

#### Create locale

```json
{
    "city" : "exemplo",
    "state" : "exemplo"
}
```

