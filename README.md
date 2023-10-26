# docker-compose-blue-green

A sample Spring Boot application to experiment with Blue - Green deployments with Docker Compose

## Background

I wanted to experiment with using Docker Compose for deployment on server environments and how to implement a graceful blue-green deployment with zero downtime when deploying a newer version of the application.

In order to have a consistent URL exposed for our clients, we need a reverse proxy in front of the actual applications being deployed. For this proxy I am using Traefik, which also runs as a Docker container.

## Prerequisites

- Java 17
- Docker CE
- Docker Compose

## Running Application

### 1. Building Docker Image
First we need to build the sample application to create a Docker image that can be deployed using Docker Compose.

#### Windows
```bash
gradlew.bat bootBuildImage
```

#### Linux
```bash
./gradlew bootBuildImage
```

### 2. Start Traefik
We want to start our Traekik reverse proxy using Docker Compose.

```bash
docker compose -f docker-compose-traefik.yml up -d
```

### 3. Start Blue Container
We want to start our application container for the blue environment using Docker Compose.

```bash
docker compose -f docker-compose-blue.yml up -d
```

### 4. Start Green Container
We want to start our application container for the green environment using Docker Compose.

```bash
docker compose -f docker-compose-green.yml up -d
```

### 5. View Application Page
Navigate your web browser to:  
http://docker.localhost
You should see a blue screen.

### 6. Switch to Green Environment
In the __dynamic__ subfolder there are 3 files.
The file used to route the URL http://docker.localhost to the blue or the green environment is __main-router.yml__.

Copy the contents of __main-router.yml.green__ to __main-router.yml__.
```bash
cp ./dynamic/main-router.yml.green ./dynamic/main-router.yml
```

### 6. View Application Again
Refresh your web browser at:  
http://docker.localhost
You should see a green screen.