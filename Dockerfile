FROM openjdk:8-jdk-alpine
EXPOSE 8082
ADD target/timesheet-Devops-1.0.0.jar timesheet-Devops-1.0.0.jar
ENTRYPOINT ["java","-jar","/timesheet-Devops-1.0.0.jar"]