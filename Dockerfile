FROM adoptopenjdk/openjdk16:jre-16.0.1_9-alpine AS builder

COPY gradlew .
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY gradle gradle
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM adoptopenjdk/openjdk16:jre-16.0.1_9-alpine

RUN mkdir /opt/app
COPY --from=builder build/libs/*.jar /opt/app/memoria.jar
EXPOSE 8080
ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
ENTRYPOINT ["java", "-jar", "/opt/app/memoria.jar"]
