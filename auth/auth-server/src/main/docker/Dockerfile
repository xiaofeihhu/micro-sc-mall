FROM livingobjects/jre8
VOLUME /logs
ADD auth-server-0.0.1-SNAPSHOT.jar auth-server-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /auth-server-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8020
ENTRYPOINT ["java","-jar","/auth-server-0.0.1-SNAPSHOT.jar"]
