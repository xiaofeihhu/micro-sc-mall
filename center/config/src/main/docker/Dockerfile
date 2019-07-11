FROM livingobjects/jre8
VOLUME /logs
ADD config-0.0.1-SNAPSHOT.jar config-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /config-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8100
ENTRYPOINT ["java","-jar","/config-0.0.1-SNAPSHOT.jar"]
