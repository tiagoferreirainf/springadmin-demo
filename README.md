# Demo: SpringAdmin + Eureka
POC project to explore the capabilities of SpringAdmin and Eureka service discovery in a kubernetes environment

## Requirements:
- Java JDK 11.
- Maven 3.8.4
- Docker
- Helm 3.7.2
- K8s cluster 

Tests are done in a local k8s instance (microk8s)


## Build/Compile
At project root run "mvn clean install -Pdocker.registry=YOUR_DOCKER_REGISTRY"

## Artifacts generated:
This project will generate 4 docker images and 4 helm charts

### Docker images:
- SpringAdmin - Server of SpringAdmin prepared to startup and expose port 8082.
- Eureka - Server of Eureka prepared to startup and expose port 8082.
- UserManagement - Demo microservice 1 to test servers capabilities.
- XXXXXXX - Demo microservice 2 to test servers capabilities.

### Helm charts:
- SpringAdmin - Helm chart with necessary configuration to run SpringAdmin Server.
- Eureka - Helm chart with necessary configuration to run Eureka Server.
- UserManagement - Helm chart with necessary configuration to run demo microservice Usermanagement.
- XXXXXXX - Helm chart with necessary configuration to run demo microservice XXXXXXX.
- MongoDB - Helm chart with necessary configuration to run a MongoDB database.