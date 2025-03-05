# Monster API

The purpose of this repository is to spin up a Rest Endpoint to act as an API to read and retrive information on Monsters.

This repository has:
* Models (Class Objects)
* Controllers (HTTP Endpoints)
* Controlled Layer Tests (JUnit, MockMvc)

## Models
The Monster Model has:
- ID
- Name
- Type
- Getters and Setters and a ToString method

## Controller
The Monster Controller has:
- A self-contained Array list of monsters (pretending to be a DB)
- HTTP Get to get all monsters
- HTTP Post to post a new monster

## Tests

### Controlled Layer Tests

#### MonsterController
- A JUnit test for the Monster Controller 
- It only loads the Monster Controller 
- It uses a Mocked HTTP server called a MockMvc to simulate HTTP calls 
- The test uses a 'perform' and 'andExpect' syntax