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