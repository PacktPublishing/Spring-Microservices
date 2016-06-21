
A. Testing the examples locally
-------------------------------------------------------

1.	Install Docker from the official Docker site.
	https://www.docker.com

2. Get the IP address of the machine and update 
	all bootstrap.properties inside projects 
	*.properties in the configuration repository

3. Change the RabbitMQ configuration file, rabbitmq.config, 
	uncomment the line below to provide access to guest. By default, guest is restricted to access from localhost only. 
    {loopback_users, []}
	The location of rabbitmq.config will be different for different operating systems.

4. Build projects 
	mvn -Dmaven.test.skip=true clean install

5. Build Docker images from the respective folders 
	docker build –t search:1.0 .
	docker build –t search-apigateway:1.0 .
	docker build –t website:1.0 .

6. Run Services
	java -jar target/config-server-1.0.jar
	java -jar target/eureka-server-1.0.jar
	docker run --net host -p 8090:8090 -t search:1.0
	docker run --net host -p 8095:8095 -t search-apigateway:1.0
	docker run --net host -p 8001:8001 -t website:1.0

7. Test Search by using, where ip address is the docker-machine ip in the case of Mac
	http://192.168.99.100:8001


B. Testing the examples in AWS
-------------------------------------------------------

1. Launch a new EC2 instance. In this case, if we have to run all instances together, we may need a large instance. The example uses t2.large 
   In this example, below Ubuntu AMI image is used. 
	ubuntu-trusty-14.04-amd64-server-20160114.5 (ami-fce3c696)

2.	Connect to the EC2 instance and run below commands. 
	sudo apt-get update 
	sudo apt-get install docker.io

3.  Install Git 
	sudo apt-get install git

4.  Create a Git repository on any folder of choice. 
	
5.	Change the config-server bootstrap.properties to point to the appropriate Git repository created for this example. 
	Change the bootstrap.properties of  all the microservices to point to the config-server using the private IP address of the EC2 instance. 
	Copy all *.properties from the local Git repository to the EC2 Git repository and perform commit. 
	Change the *.properties Eureka-server URLs and Rabbit MQ URLs to match the EC2 private IP address. Commit changes to Git once it is completed. 
	On the local machine re-compile all projects and create Docker images for search, search-apigateway and website microservices. 

6. Build projects 
	mvn -Dmaven.test.skip=true clean install

7. Build Docker images from the respective folders 
	docker build –t search:1.0 .
	docker build –t search-apigateway:1.0 .
	docker build –t website:1.0 .

8.	Push all of them to the Docker Hub registry. This step need an account with Docker Registry ( in this case docker hub user is brownfield)
	docker tag search:1.0 brownfield/search:1.0
	docker push brownfield/search:1.0

	docker tag search:1.0 brownfield/search-apigateway:1.0
	docker push brownfield/search-apigateway:1.0

	docker tag search:1.0 brownfield/search:1.0
	docker push brownfield/search:1.0	

9.	Copy the config-server and the eureaka-server binaries from the local machine to the EC2 instance.
	Setup Java 8 on the EC2 instance.  (http://aws-labs.com/ubuntu-install-java-8/)
	
	Execute below commands in sequence. 

	java –jar config-server.jar 
	java –jar eureka-server.jar 
	docker run –net host rabbitmq3
	docker run --net host -p 8090:8090 rajeshrv/search:1.0
	docker run --net host -p 8095:8095 rajeshrv/search-apigateway:1.0
	docker run --net host -p 8001:8001 rajeshrv/website:1.0 

10. Validate whether all services are working by opening the URL of the website and execute search. Note that we will be using the public IP in this case. 
	http://54.165.128.23:8001   
