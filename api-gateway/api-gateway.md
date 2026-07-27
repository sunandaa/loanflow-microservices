# API Gateway

## Purpose

The LoanFlow API Gateway is the single external entry point for
all LoanFlow APIs.

## Port

8080

## Eureka Registration

The gateway registers with Eureka using the application name:

```text
api-gateway

## Running Locally

### 1. Start Service Registry

```bash
mvn -pl service-registry spring-boot:run