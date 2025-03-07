# Monster API

## Description

This is a Java Springboot Rest API that uses traditional Springbook Rest architecture.

Architecture:
Data Flow
```
(Client) -> Controller -> Service -> Repository -> Module
```
Response Flow
```
Module -> Repository -> Service -> Controller -> (Client)
```

In this project:
```
Module = /models/Monster
Repostory = /repositories/MonsterRepository
Service = /services/MonsterService
Controller = controllers/MonsterController
```

## Tests

Here is a breakdown:

| Layer            | Type of Test                 | Tools Used                         |
|------------------|------------------------------|------------------------------------|
| Model Layer      | Unit Tests, Validation Tests | JUnit 5, AssertJ                   |
| Repository Layer | Integration Tests            | Spring Boot Test, H2, @DataJpaTest |
| Service Layer    | Unit Tests (mock repository) | JUnit 5, Mockito                   |
| Controller Layer | API Tests (mock service)     | Spring Boot Test, MockMvc, Mockito |

In short:
- Unit Tests → For isolated logic (Service, Model). 
- Integration Tests → For database interactions (Repository). 
- API Tests → For testing HTTP endpoints (Controller).