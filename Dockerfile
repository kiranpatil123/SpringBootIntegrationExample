FROM openjdk:11
EXPOSE 9090
ADD target/springbootintegration.jar springbootintegration.jar
ENTRYPOINT ["java","-jar","/springbootintegration.jar "]