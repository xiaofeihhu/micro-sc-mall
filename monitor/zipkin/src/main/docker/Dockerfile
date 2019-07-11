FROM livingobjects/jre8
VOLUME /logs
ADD zipkin-server-2.12.9-exec.jar zipkin-server-2.12.9-exec.jar
RUN sh -c 'touch /zipkin-server-2.12.9-exec.jar'
RUN echo "Asia/Shanghai" > /etc/timezone
EXPOSE 9411
ENTRYPOINT ["java","-jar","/zipkin-server-2.12.9-exec.jar"]
