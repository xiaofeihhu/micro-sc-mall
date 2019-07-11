FROM livingobjects/jre8
VOLUME /logs
ADD eureka-0.0.1-SNAPSHOT.jar eureka-0.0.1-SNAPSHOT.jar
RUN sh -c 'touch /eureka-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8761
ENTRYPOINT ["java","-jar","/eureka-0.0.1-SNAPSHOT.jar"]
