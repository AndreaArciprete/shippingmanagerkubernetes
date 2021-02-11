FROM maven:3-openjdk-15-slim as builder
WORKDIR /project
COPY . .
RUN mvn package

FROM openjdk:15-alpine
WORKDIR /app
COPY --from=builder /project/target/shippingmanagerkubernetes-0.0.1-SNAPSHOT.jar ./shippingmanagerkubernetes.jar

ENTRYPOINT ["/bin/sh", "-c"]
CMD  [ "java -jar shippingmanagerkubernetes.jar" ]


