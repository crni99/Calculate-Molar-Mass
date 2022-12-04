FROM openjdk:17-oracle
EXPOSE 8080
ADD target/calculate-molar-mass.jar calculate-molar-mass.jar
ENTRYPOINT ["java", "-jar", "/calculate-molar-mass.jar"]