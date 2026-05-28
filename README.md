# Blackjack API - Spring WebFlux

**Description**: Reactive REST API for playing Blackjack with Spring Boot, WebFlux, MySQL and MongoDB integration.

## 📌 Exercise Statement

S5.01 - Advanced Spring Framework with WebFlux

A three-level project implementing a reactive Blackjack API:
- **Level 1**: Reactive API development with Spring WebFlux, dual database support
- **Level 2**: Containerization with Docker, multi-stage builds, Docker Hub deployment
- **Level 3**: Cloud deployment with Render and GitHub Actions

## ✨ Features

- **Reactive API**: Spring WebFlux for non-blocking operations
- **Dual Database**: MySQL for players (JPA), MongoDB for games (Reactive)
- **Game Logic**: Complete Blackjack rules implementation
- **Documentation**: OpenAPI/Swagger UI for all endpoints
- **Exception Handling**: Global error handling with custom exceptions
- **Testing**: Unit tests for services and integration tests for controllers
- **Docker**: Multi-stage Dockerfile with optimized layers
- **Cloud Ready**: Deployed on Render with automatic GitHub integration

## 🛠 Technologies

- **Backend**: Java 21, Spring Boot 3.5.3, Spring WebFlux
- **Databases**: MySQL 8.0 (JPA), MongoDB 7.0 (Reactive)
- **Tools**: Maven, Docker, Docker Compose
- **Testing**: JUnit 5, Mockito, WebTestClient
- **Documentation**: SpringDoc OpenAPI 2.8.6, Swagger UI
- **Deployment**: Render, Docker Hub, GitHub

## 🚀 Installation and Execution

### Local Development

1. **Clone the repository:**
```bash
   git clone https://github.com/marccasadevall/Tasca-S5.01-Spring-Framework-Avançat-amb-WebFlux.git
   cd blackjack-api
```

2. **Environment Setup:**
```bash
   # Create .env file
   MYSQL_ROOT_PASSWORD=root
   MYSQL_DATABASE=blackjack
   MYSQL_USER=blackjack_user
   MYSQL_PASSWORD=blackjack_pass
   
   MONGO_INITDB_ROOT_USERNAME=admin
   MONGO_INITDB_ROOT_PASSWORD=admin
```

3. **Start containers:**
```bash
   docker-compose up -d
```

4. **Build and run the application:**
```bash
   mvn clean install -DskipTests
   mvn spring-boot:run
```

5. **Access the application:**
    - API: http://localhost:8080/player/ranking
    - Swagger UI: http://localhost:8080/swagger-ui.html

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=BlackjackRulesServiceTest
```

### Docker Deployment

```bash
# Build image
docker build -t blackjack-api:1.0.0 .

# Run container
docker run -d -p 8080:8080 \
  --network blackjack-api_blackjack-network \
  --name blackjack-app \
  blackjack-api:1.0.0
```

## 📸 Demo

**Live URL**: https://blackjack-api-jsz6.onrender.com

**Available Endpoints:**
- GET `/player/ranking` - Get player rankings
- POST `/player` - Create new player
- GET `/game/{id}` - Get game details
- POST `/game/new` - Start new game
- POST `/game/{id}/play` - Execute play action
- DELETE `/game/{id}/delete` - Delete game

## 🧩 Technical Decisions & Architecture

### Database Strategy
- **MySQL**: Stores persistent player data (JPA/Hibernate)
- **MongoDB**: Stores game snapshots (Reactive Streams)
- **Rationale**: Relational data for players, document-based for flexible game states

### Reactive Programming
- **WebFlux**: Non-blocking I/O for scalability
- **Mono/Flux**: Reactive types for async operations
- **Rationale**: Better resource utilization, higher throughput

### Docker Architecture
- **Multi-stage build**: Reduces final image size (500MB → 100MB)
- **Alpine Linux**: Lightweight runtime image
- **Health checks**: Ensures database readiness

### Cloud Deployment
- **Render**: Easy GitHub integration, automatic deployments
- **Environment Variables**: Secure credential management
- **Rationale**: Serverless simplicity, auto-scaling capabilities

## 📋 Project Structure
src/
├── main/java/com/blackjack/blackjack_api/
│   ├── model/          # Domain entities (Card, Hand, Player, Game)
│   ├── dto/            # Data transfer objects
│   ├── repository/     # Data access layer
│   ├── service/        # Business logic
│   ├── controller/     # REST endpoints
│   ├── exception/      # Custom exceptions & handlers
│   └── config/         # Spring configuration
└── test/               # Unit & integration tests