Follow the steps below to run the lifecycle manager developed in the previous section.  

1. Edit code
----------------------------------------
Edit the DeploymentEngine.java and update the password to reflect the machines password. 
This is required for the SSH connection. 
session.setPassword("rajeshrv");

2. Build Code
-----------------------------------------
Build all projects by running maven from the root folder (chapter6)
mvn -Dmaven.test.skip=true clean install

3. Run Rabbit MQ. 
------------------------------------------
./rabbitmq-server

4. Update Configuration Repository
------------------------------------------
Ensure that the Config server point to the right configuration repository. We need to add a property file for the lifecycle manager. 
The sample configuration repository is copies as "chapter6-config-repo". Make sure git repository is created and commited the files.

5. Run below commands from the respective project folders. Make sure, 50-60 seconds gap between each project execusion.
----------------------------------------------
java -jar target/config-server-0.0.1-SNAPSHOT.jar
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar
java -jar target/lifecycle-manager-0.0.1-SNAPSHOT.jar
java -jar target/search-1.0.jar
java -jar target/search-apigateway-1.0.jar
java -jar target/website-1.0.jar

6. Run Client
---------------------------------------
Once all services are started, open a browser window and load http://localhost:8001
Execute flight search 11 times, one after the other, within a minute. This will trigger the decision engine to instantiate another instance of Search microservice.  
Open Eureka console (http://localhost:8761) and watch for a second SEARCH-SERVICE.  
