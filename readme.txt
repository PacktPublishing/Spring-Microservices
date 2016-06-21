
Follow the steps below to run and test Chapter 5

Run Rabbit MQ
----------------------------------------------
./rabbitmq-server

Build all projects using the pom.xml at the root level. 
-------------------------------------------------
mvn –Dmaven.test.skip=true clean install 

Run 
-------------------------------------------
Run below projects from the respective folders. 

Hint: Note that wait for 40-60 seconds before starting the next service. This will ensure that the dependent services are registered and are available before we starting a new service.

java -jar target/fares-1.0.jar
java -jar target/search-1.0.jar
java -jar target/checkin-1.0.jar
java -jar target/book-1.0.jar
java –jar target/fares-apigateway-1.0.jar
java –jar target/search-apigateway-1.0.jar
java –jar target/checkin-apigateway-1.0.jar
java –jar target/book-apigateway-1.0.jar
java -jar target/website-1.0.jar

Open the browser window and point to http://localhost:8001. 