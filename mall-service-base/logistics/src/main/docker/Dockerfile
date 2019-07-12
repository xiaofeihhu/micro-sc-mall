FROM livingobjects/jre8
VOLUME /logs
ADD logistics-0.0.1-SNAPSHOT.jar logistics-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /logistics-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8089
ENTRYPOINT ["java","-jar","/logistics-0.0.1-SNAPSHOT.jar"]
