# Contiero Cellar

This project implements a REST service to keep records of wines in a cellar. It is written in Java using Spring Boot to perform CRUD operations on MySQL database.

## Why are we doing this?

This project is part of the requirement for the completion of QA Digital Skills Bootcamp where the fundamentals of Software Development were covered.

The objective is to create a Spring Boot REST API, with utilisation of supporting tools, methodologies, and technologies, that encapsulates all fundamental and practical modules covered during training.

# QA Academy Requirements 

## What went well?

Jira as an Agile project management tool was an extremely valuable instrument for the planning of the whole project as it allowed to layout the work ahead from the big picture to the fine details. Also, Spring Boot Framework was undoubtedly the most helpful in the whole coding experience. 

## What didn't go as planned?

Some tasks took more effort to implement than originally expected. This happened, for example, during the development of the first action: creating an entity (`/wine/create`) where much more code was needed than expected. As a result, part of this code was later reused by other actions making subsequent tasks less time consuming. 

## Possible improvements for future revisions of the project.

It would be nice to have another controller (possibly under `/status`) containing information about the current status of the Cellar, such as: the total number bottles, total value of the cellar, the most expensive and the cheapest wine, or a list of wines containing low number of bottles that need to be back ordered.


## Screenshots of postman requests with the output from the API.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/18804407-896cf0e9-3557-44be-9867-516e6cf41556?action=collection%2Ffork&collection-url=entityId%3D18804407-896cf0e9-3557-44be-9867-516e6cf41556%26entityType%3Dcollection%26workspaceId%3D28bbb8b7-61d6-48bc-b5fa-d612af4acb68)

### Test 1 - Create Wine entity

![Test 1 - Create Entity](doc/postman/Test1_create.PNG)


### Test 2 - Read all wine entries

![Test 2 - Read all wine entries](doc/postman/Test2_readAll.PNG)


### Test 3 - Edit entry

![Test 3 - Edit entry](doc/postman/Test3_edit.PNG)


### Test 4 - Delete entry

![Test 4 - Delete entry](doc/postman/Test4_delete.PNG)


### Test 5 - Get wine entry by type

![Test 5 - Get wine entry by type](doc/postman/Test5_getByType.PNG)


### Test 6 - Get entries cheaper than amount

![Test 6 - Get entries cheaper than amount](doc/postman/Test6_getCheaperThan.PNG)


### Test 7 - Get entries by region

![Test 7 - Get entries by region](doc/postman/Test7_getByRegion.PNG)


### Test 8 - Get entries by type and under price

![Test 8 - Get entries by type and under price](doc/postman/Test8_getByTypeAndPrice.PNG)


### Test 9 - Get entries by producer

![Test 9 - Get entries by producer](doc/postman/Test9_getByProducer.PNG)


## Screenshot of database.

![Database](doc/MySQL.PNG)

## Screenshots of test results.

Spring Application Test

![Spring Application Test](doc/tests_screenshots/SpringApplicationTest.png)

Wine Entity Unit Test

![Wine Entity Unit Test](doc/tests_screenshots/WineTest.png)

Wine Service Unit Test

![Wine Service Unit Test](doc/tests_screenshots/WineServiceUnitTest.png)

Wine Controller Unit Test

![Wine Controller Unit Test](doc/tests_screenshots/WineControllerUnitTest.png)

Wine Controller Integration Test

![Wine Controller Integration Test](doc/tests_screenshots/WineControllerIntegrationTest.png)


Tests were also run from the command line using `./mvnw test` and the output was collected and located in [doc/test.log](doc/test.log)

## Link to Jira Board. 

https://contieroelza.atlassian.net/jira/software/projects/CC/boards/4/backlog

## ERD Diagram

Entity Relationship Diagram for the database.

|**Wine**| |
|-|-|
|ID|BIGINT AUTOINCREMENT PRIMARY KEY|
|NAME|VARCHAR(255) NOT NULL|
|NUMBER_OF_BOTTLES | INT| 
|PRICE | DOUBLE | 
|PRODUCER | VARCHAR(255) |
|REGION | VARCHAR(255) |
|TYPE | VARCHAR(255) |
|YEAR | INT |
