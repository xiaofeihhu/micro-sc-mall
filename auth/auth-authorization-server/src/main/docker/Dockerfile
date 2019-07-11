FROM livingobjects/jre8
VOLUME /logs
ADD auth-authorization-server-0.0.1-SNAPSHOT.jar auth-authorization-server-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /auth-authorization-server-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8000
ENTRYPOINT ["java","-jar","/auth-authorization-server-0.0.1-SNAPSHOT.jar"]
