FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/blackjack-api-*.jar app.jar

EXPOSE 8080

ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/blackjack?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
ENV SPRING_DATASOURCE_USERNAME=blackjack_user
ENV SPRING_DATASOURCE_PASSWORD=blackjack_pass
ENV SPRING_DATA_MONGODB_URI=mongodb://admin:admin@mongodb:27017/blackjack_games?authSource=admin

ENTRYPOINT ["java", "-jar", "app.jar"]