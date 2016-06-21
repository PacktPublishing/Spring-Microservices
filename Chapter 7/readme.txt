
1. Build all project from the root (chapter7)
------------------------------------------------
mvn -Dmaven.test.skip=true clean install

A. For Running Logging example.
------------------------------------------------

1. Install Elasticsearch, Kibana and Logstash
2. Run Elasticsearch , Kibana and Logstash
   	./bin/logstash -f logstash.conf
	./bin/elasticsearch
	./bin/kibana

3. Run Rabbit MQ
	./rabbitmq-server
4. Run Congig Server, Eureaka and Search Service, Search API Gateway, from the respective folders. 
	java -jar target/config-server-1.0.jar
	java -jar target/eureka-server-1.0.jar
	java -jar target/chapter7.search-1.0.jar
	java -jar target/chapter7.search-apigateway-1.0.jar
	java -jar target/website-1.0.jar

5. Open Browser window 
	http://localhost:8095/hubongw
	http://localhost:8001

B. For Running Hystrix Example
-----------------------------------------------

1. Follow  steps above upto #5. 

2. Run Hystrix dashboard
	java -jar target/hystrixdashboard-0.0.1-SNAPSHOT.jar

3. Open Hystrix Dashboard
	http://localhost:9999/hystrix.stream

	point to 
	http://localhost:8095/hystrix.stream
	Note: fire some transactions on the link below to get the dashboard display
	http://localhost:8095/hubongw

C. For Running Turbine Example
--------------------------------------------------

1. Follow  steps defined in the seciton A, upto #5. 

2. Update /etc/hosts and add localdomain1 and localdomain2 mapto localhost

3. Stop serch, search api gateway, website and hystrix dashboard services

4. Run services
	java -jar -Dserver.port=8096 -Deureka.instance.hostname=localdomain2 -Dserver.address=localdomain2 target/chapter7.search-apigateway-1.0.jar
	java -jar -Dserver.port=8095 -Deureka.instance.hostname=localdomain1 -Dserver.address=localdomain1 target/chapter7.search-apigateway-1.0.jar
	java -jar -Dserver.port=8090 -Deureka.instance.hostname=localdomain1 -Dserver.address=localdomain1 target/chapter7.search-1.0.jar

	java -jar target/website-1.0.jar

5. Run Turbine dashboard
	java -jar target/turbineserver-1.0.jar

6. Open Turbine Dashboard
	http://localhost:9090/turbine.stream

	point to 
	http://localhost:8090/turbine.stream
	Note: fire some transactions on the link below to get the dashboard display
	http://localhost:8095/hubongw
	http://localhost:8095/hubongw
	http://localhost:8001/	







