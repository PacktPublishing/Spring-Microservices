
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/website-1.0.jar website.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/website.jar"]
