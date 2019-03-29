FROM openjdk:8-alpine AS build  
COPY . /usr/src/app/  
WORKDIR /usr/src/app/  
RUN ./gradlew clean customFatJar

FROM openjdk:8-alpine
COPY --from=build /usr/src/app/build/libs/cd_demo_movie_sevice-all-1.0-SNAPSHOT.jar /usr/app/app.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]  
