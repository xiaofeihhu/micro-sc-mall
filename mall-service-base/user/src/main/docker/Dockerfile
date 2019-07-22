FROM livingobjects/jre8
VOLUME /logs
ADD user-0.0.1-SNAPSHOT.jar user-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /user-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8083
ENTRYPOINT ["java","-jar","/user-0.0.1-SNAPSHOT.jar"]
