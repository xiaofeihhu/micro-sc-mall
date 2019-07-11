FROM livingobjects/jre8
VOLUME /logs
ADD service-consumer-0.0.1-SNAPSHOT.jar service-consumer-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /service-consumer-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8081
ENTRYPOINT ["java","-jar","/service-consumer-0.0.1-SNAPSHOT.jar"]
