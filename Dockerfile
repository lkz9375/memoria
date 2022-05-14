FROM adoptopenjdk:16-jdk-hotspot AS builder

COPY gradlew .
COPY settings.gradle.kts .
COPY build.gradle.kts .
COPY gradle gradle
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM arm64v8/openjdk:18-slim

RUN mkdir /opt/app
COPY --from=builder build/libs/*.jar /opt/app/memoria.jar
EXPOSE 8080
ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENTRYPOINT ["java", "-jar", "/opt/app/memoria.jar"]
