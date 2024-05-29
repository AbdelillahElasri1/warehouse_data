# 🚀 ClusteredData Warehouse API Documentation

ClusteredData Warehouse exposes a powerful API for handling Foreign Exchange (FX) deals. This documentation details the `/api/FXDeals` endpoint, its input structure, validation, error handling, and deployment using Docker.

## API Endpoint

### Endpoint:
- **[POST] http://localhost:8082/api/FXDeals**

### Request JSON Example:
```json
{
  "id":"ghsdf234234",
  "orderingCurrencyIsoCode": "CODE123",
  "toCurrencyIsoCode": "CODE321",
  "dealAmount": 8500.99
}
```
## Project Structure:
```javascript
.
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── Makefile
├── mvnw
├── mvnw.cmd
├── pom.xml
├── readme.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── datawarehouse
│   │   │           ├── Aspect
│   │   │           │   └── MethodLoggingAspect.java
│   │   │           ├── configs
│   │   │           │   └── BeanConfig.java
│   │   │           ├── controller
│   │   │           │   └── FXDealsController.java
│   │   │           ├── DataWarehouseApplication.java
│   │   │           ├── exceptions
│   │   │           │   ├── GlobalExceptionHandler.java
│   │   │           │   └── RequestAlreadyExistException.java
│   │   │           ├── model
│   │   │           │   ├── dtos
│   │   │           │   │   └── FXDealsDto.java
│   │   │           │   └── entities
│   │   │           │       └── FXDeals.java
│   │   │           ├── repository
│   │   │           │   └── FXDealsRepository.java
│   │   │           └── service
│   │   │               ├── FXDealsService.java
│   │   │               └── implementation
│   │   │                   └── FXDealsServiceImplementation.java
│   │   └── resources
│   │       ├── application.yml
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── datawarehouse
│                   ├── DataWarehouseApplicationTests.java
│                   └── FXDealsServiceImplTest.java
└── target
    ├── classes
    │   └── application.yml
    ├── generated-sources
    │   └── annotations
    └── maven-status
        └── maven-compiler-plugin
            └── compile
                └── default-compile
                    └── inputFiles.lst
```
## Run application with docker:
```shell
docker compose up 
```
Or using MakeFile
```javascript
Makefile:

run:
	docker compose up
run-detached:
	docker compose up -d
down:
	docker compose down
```
```shell
make run
```

## Request Validation

The endpoint performs field validation using Jakarta Validation annotations. Any invalid field triggers a structured error message handled by the `GlobalExceptionHandler` REST Controller Advice, responding with HTTP status `BAD_REQUEST`.

## Database Connection:
#### Run database container in docker:
```shell
docker exec -it warehouse-postgres-db bash
```

#### Connect to postgresql database with user and database name:
```shell
psql -U abdelillah -d warehouse
```


## Database Interaction

- If the FX deal with the specified `id` does not exist in the Postgres database:
    - The object is inserted.
    - The inserted object is returned.

- If the FX deal with the specified `id` already exists in the database:
    - An exception is thrown.
    - The exception is caught and handled by the `GlobalExceptionHandler` class.
    - The response includes a structured error message with HTTP status `BAD_REQUEST`.

## Aspect-Oriented Programming (AOP) Logging

AspectJ is utilized for AOP logging to capture parameters passed to service methods. This includes logging successful executions and errors, providing comprehensive insights.

## Testing

- **JUnit 5:**
    - The project employs JUnit 5 for unit testing.
    - Extensive test coverage ensures robustness, achieving 100% coverage.

- **Mockito:**
    - Mockito is utilized for efficient mocking in unit tests.

## Dockerization

The application is Dockerized using a multi-stage Dockerfile, optimizing the Docker image size and ensuring efficient deployment.

### Docker Compose

Docker Compose is employed to orchestrate the deployment of two services:
- The Spring Boot application [PORT 8082].
- Postgres 15, serving as the database [PORT 5432].

## Running the Application

# Run Docker Compose (foreground)
make run

# Run Docker Compose (detached)
make run-detached

#Access the API:
The API is now accessible at - **[POST] http://localhost:8082/api/FXDeals**

## Stop the Application
make down