FROM openjdk:21-oracle AS build
WORKDIR /stockexchange
COPY . /stockexchange
RUN ./mvnw clean install

FROM openjdk:21-oracle AS run
WORKDIR /stockexchange
COPY --from=build /stockexchange/target/stockexchange-0.0.1-SNAPSHOT.jar /stockexchange/stockexchange.jar
EXPOSE 8080
CMD ["java", "-jar", "/stockexchange/stockexchange.jar"]
