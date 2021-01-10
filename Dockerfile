FROM openjdk

LABEL version="1.0"
LABEL description="Cake Management System"

COPY ./target/cake-manager.jar /opt/waracle/cake-manager.jar

# ENV JAVA_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005

EXPOSE 8080 18080

# ARG privilege

CMD /opt/waracle/cake-manager.jar 

