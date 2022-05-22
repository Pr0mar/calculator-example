# Simple calculator

### I used technologies

- Java,
- Spring boot, 
- H2 database,
- Swagger,
- Lombok,
- Angular

Project was split into 3 modules: 
- parent,
- two children (frontend and backend)

We are building big jar which contains the frontend and backend.
The parent pom.xml declares the 2 modules to build.
In root project please run: 

**1. mvn clean package** 
**2. java -jar backend/target/backend-0.1-SNAPSHOT.jar**

Result should be like below: 






