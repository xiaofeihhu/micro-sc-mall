FROM livingobjects/jre8
VOLUME /logs
ADD zuul-0.0.1-SNAPSHOT.jar zuul-0.0.1-SNAPSHOT.jar
ADD wait-for.sh /wait-for.sh
RUN chmod 777 /wait-for.sh
RUN sh -c 'touch /zuul-0.0.1-SNAPSHOT.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 8080
ENTRYPOINT ["java","-jar","/zuul-0.0.1-SNAPSHOT.jar"]
