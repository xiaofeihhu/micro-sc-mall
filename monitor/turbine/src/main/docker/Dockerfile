FROM livingobjects/jre8
VOLUME /logs
ADD turbine-0.0.1-SNAPSHOT.jar turbine-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /turbine-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8023
ENTRYPOINT ["java","-jar","/turbine-0.0.1-SNAPSHOT.jar"]
