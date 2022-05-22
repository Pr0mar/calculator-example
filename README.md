# Simple calculator

### I used technologies

- Java,
- Spring boot, 
- H2 database,
- Swagger,
- Lombok,
- Angular

### How to run app


Project was split into 3 modules: 
- parent,
- two children (frontend and backend)

We are building big jar which contains the frontend and backend.

The parent pom.xml declares the 2 modules to build.

In root project please run: 

**1. mvn clean package** 

**2. java -jar backend/target/backend-0.1-SNAPSHOT.jar**

Result should be like below http://localhost:8080/ (without operations): 

<img width="1439" alt="Zrzut ekranu 2022-05-22 o 22 45 48" src="https://user-images.githubusercontent.com/34887398/169717014-c67a753e-d531-409e-8b9a-11119de44190.png">

Screenshot contains few operations 

The modules can be started separately during the development. The Angular will use the port 4200, the Java application will use the port 8080.


#### Swagger

We can test backend side using swagger-ui: http://localhost:8080/swagger-ui/index.html

<img width="1437" alt="Zrzut ekranu 2022-05-22 o 22 45 41" src="https://user-images.githubusercontent.com/34887398/169717335-8228001d-7ad4-4edb-886e-630a3900e93b.png">


### TESTS 

Under path: **calculator-example/backend/src/test**

Extra: 
I added handling  brackets.
Possible display last operations









