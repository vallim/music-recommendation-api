# Music Recommendation Api

This application aims to return music recommendations based on city or latitude and longitude parameters. You can call it by passing the city:

```
http://localhost:8080/recommendations?city=Campinas
```

or by latitude and longitude

```
http://localhost:8080/recommendations?lat=22.9099&long=47.0626
```

# Starting the application

This application is based on **Docker**. So, you should have it installed on your machine to be able to run the application as a container. In the project root, you can execute the run.sh script to start the application:

```
./run.sh
```
To stop the application, you can follow the same idea executing stop.sh:

```
./stop.sh
```

# Technologies

This application is strongly based on Spring ecosystem technologies, such as:

* Spring Boot 2.0.2
* Spring Cache
* Spring Retry
* Spring Cloud Sleuth
* WireMock
* Lombok
