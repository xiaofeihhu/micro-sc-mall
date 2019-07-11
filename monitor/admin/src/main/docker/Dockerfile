FROM livingobjects/jre8
VOLUME /logs
ADD admin-0.0.1-SNAPSHOT.jar admin-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /admin-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8022
ENTRYPOINT ["java","-jar","/admin-0.0.1-SNAPSHOT.jar"]
