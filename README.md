# LoanFlow Microservices

LoanFlow is a production-style Loan Origination System developed using
Spring Boot, Spring Cloud and event-driven microservices architecture.

## Business Capabilities

- Customer onboarding
- Loan application management
- Eligibility calculation
- Document verification
- Loan approval workflow
- Event-driven notifications
- Audit tracking
- Authentication and authorization

## Technology Stack

- Java 17
- Spring Boot
- Spring Cloud
- Spring Data JPA
- PostgreSQL
- Apache Kafka
- Resilience4j
- Keycloak
- Docker
- Maven
- JUnit 5
- Testcontainers

## Microservices

| Service | Port | Responsibility |
|---|---:|---|
| Service Registry | 8761 | Service discovery |
| Config Server | 8888 | Centralized configuration |
| API Gateway | 8080 | Routing and security |
| Customer Service | 8081 | Customer management |
| Loan Application Service | 8082 | Loan application workflow |
| Eligibility Service | 8083 | Eligibility calculation |
| Notification Service | 8084 | Email/SMS notifications |
| Audit Service | 8085 | Audit event storage |

## Project Status

Project setup in progress.