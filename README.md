1. Project Overview
- Short intro: MyMart — a Spring Boot backend with MySQL, Dockerized for portability and deployment.

2. Testing Notes
- Controller Layer Testing
- @AutoConfigureMockMvc → creates MockMvc instance.
- @WebMvcTest → detects only controller, mocks dependencies manually.
- @SpringBootTest → loads full context, auto-detects dependencies.

3. Features Implemented
- Pagination: 
- Used Pageable request object.
- Mapped Page object to product response.
- Calculated total pages = ceil(totalElements / pageSize).
- 
- Sorting: 
- Used Sort.by (like SQL ORDER BY).
- Supported ascending/descending on product columns.

- Filtering: 
- Filter by category, price, etc.
- Dynamic price range filtering with Spring Data JPA + pageable.

- Transactional Flow: 
- Multi-item order placement with @Transactional.
- Steps: product fetch → stock check → stock reduce → order save → order items save → total amount → rollback on failure.

- Async Email Notification:
- Integrated async email confirmations to improve UX and performance.

4. Dockerization
- Why: Package app + runtime → consistent across systems, portable, production-ready.
- Steps:
- Created Dockerfile (must be named exactly Dockerfile).
- Built JAR with .\mvnw clean package.
- Built image with docker build -t mymart-app ..
- Created docker-compose.yml for MyMart app + MySQL.

5. Spring Security:
- Implemented Role based security for the Authentication and Authorization.

6. Command Cheat Sheet
# Maven
.\mvnw package                  : build JAR file (runs tests by default)
.\mvnw package -DskipTests      : build JAR file without running tests
.\mvnw clean package            : delete old build files and build new JAR
.\mvnw test                     : run all tests
.\mvnw test -Dtest=ClassName    : run specific test class

# Docker
docker build -t mymart-app .    : build Docker image from Dockerfile
docker run -p 8080:8080 mymart-app : run container mapping port 8080 (initially used)
docker run -p 9999:9999 mymart-app : run container mapping port 9999 (Spring Boot app port)
docker images                   : list all built images
docker ps                       : list running containers
docker stop <container_id>      : stop a running container
docker info                     : show Docker system info
docker version                  : show Docker client/server version

# Docker Compose
docker-compose up               : start all services defined in docker-compose.yml
docker-compose up --build       : rebuild images and start services
docker-compose down             : stop and remove containers, networks, volumes

# MySQL Access
mysql -h 127.0.0.1 -P 3307 -u root -p : connect to MySQL running in Docker (host port 3307)
