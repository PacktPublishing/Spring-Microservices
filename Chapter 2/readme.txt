PRE-REQUISITE 
-------------------------------

1. intall JDK 1.8
2. Install apache-maven-3.3.1
3. Copy files to a folder names chapter2
4. install apache-tomcat-8.0.21

BUILD ALL PROJECTS
--------------------------------

open a terminal window

$cd chapter2
$mvn -Dmaven.test.skip=true clean install


PROJECT 1: legacyrest
-------------------------------
move to legacyrest project
copy target/chapter2-1.0.0-BUILD-SNAPSHOT.war to Tomcat's webapps folder 
start tomcat server

open http://localhost:8080/ in a browser window

PROJECT 2: chapter2.bootrest
-------------------------------

move to chapter2.bootrest project
$java target/bootrest-0.0.1-SNAPSHOT.jar 

open http://localhost:8080/ in a browser window


PROJECT 3: chapter2.boothateoas
-------------------------------

move to chapter2.boothateoas project
$java target/boothateoas-0.0.1-SNAPSHOT.jar 

open http://localhost:8080/ in a browser window



PROJECT 4: chapter2.boot-advanced
-------------------------------

move to chapter2.boothateoas project
$java target/bootadvanced-0.0.1-SNAPSHOT.jar 

$mvn install

PROJECT 5: chapter2.bootmessaging
-------------------------------

move to chapter2.bootmessaging project
$java target/bootmessaging-0.0.1-SNAPSHOT.jar 

$mvn install


PROJECT 6: chapter2.bootactuator
-------------------------------

move to chapter2.bootactuator project
$java target/actuator-0.0.1-SNAPSHOT.jar 

open http://localhost:8080/ in a browser window


PROJECT 7: Customer Profile & Notification 
-------------------------------

move to chapter2.bootcustomer project
$java target/customer-0.0.1-SNAPSHOT.jar

$mvn install

move to chapter2.bootcustomernotifcation project
$java target/customernotification-0.0.1-SNAPSHOT.jar

$mvn install

open http://localhost:8080/ in a browser window
create a new customer using the HAL browser


PROJECT 8: chapter2.swagger
-------------------------------

move to chapter2.bootactuator project
$java target/swagger-0.0.1-SNAPSHOT.jar 

open http://localhost:8080/swagger-ui.html in a browser window


