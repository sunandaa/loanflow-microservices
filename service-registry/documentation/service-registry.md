# Service Registry

## Purpose

The Service Registry provides service discovery for LoanFlow
microservices using Netflix Eureka.

## Port

8761

## Dashboard

http://localhost:8761

## Health Endpoint

http://localhost:8761/actuator/health

## Responsibilities

- Accept service registrations
- Maintain service instance information
- Receive service heartbeats
- Provide service location information to clients
- Support client-side load balancing

## Configuration

The registry does not register with another Eureka server and does not
fetch a registry because the local environment uses a standalone server.

```yaml
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false