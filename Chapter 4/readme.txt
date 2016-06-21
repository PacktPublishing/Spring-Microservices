
Follow below steps to run the application. 

1. Open terminal Window and move to project home. 

mvn -Dmaven.test.skip=true install

2. Start Rabbit MQ. 

rabbitmq_server-3.5.6/sbin$ ./rabbitmq-server


3.Run below commands from the respective project folders, in separate terminal windows. 

java -jar target/fares-1.0.jar
java -jar target/search-1.0.jar
java -jar target/checkin-1.0.jar
java -jar target/book-1.0.jar
java -jar target/website-1.0.jar

4. Open Browser Window and paste below URL.

http://localhost:8001

5. When asked for credentials use guest/guest123

6. Click Search Menu for search and Booking

7. Click CheckIn Menu for check0in


