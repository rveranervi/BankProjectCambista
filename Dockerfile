FROM bellsoft/liberica-openjdk-alpine-musl:11-aarch64
COPY "./target/cambista-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]