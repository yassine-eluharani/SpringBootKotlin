# Swagger 
## Swagger-url
Swagger ui
```
http://localhost:8080/swagger-ui/
``` 
springfox apps-docs
```
http://localhost:8080/v2/api-docs
``` 

# Docker
## Build app
Build with gradle
```
gradle build
```
Or try the graddle-wrapper
```
./gradlew build
```

## To create an image from our Dockerfile:
```
docker build --tag=product-api:latest .
```

## Run the container from our image
```
docker run -p8080:8080 product-api:latest
```