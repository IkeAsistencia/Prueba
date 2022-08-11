FROM amazoncorretto:11

ENV PROJECT_NAME=BanBajio
ENV VERSION=2.5.8

#RUN mkdir /$PROJECT_NAME
#WORKDIR /$PROJECT_NAME
#VOLUME /$PROJECT_NAME

ADD $PROJECT_NAME-$VERSION.jar $PROJECT_NAME-$VERSION.jar
EXPOSE 8091 8091
EXPOSE 80 8091
EXPOSE 443 8091
ENTRYPOINT ["sh", "-c", "java -jar $PROJECT_NAME-$VERSION.jar"]

#ADD ./vmwnasistappqa.pfx /$PROJECT_NAME-$VERSION.jar/vmwnasistappqa.pfx
#ADD ./bbmx.cer /$PROJECT_NAME-$VERSION.jar/bbmx.cer
#ADD ./certificate.pem /$PROJECT_NAME-$VERSION.jar/certificate.pem

#RUN yum update -y && yum install -y vim && rm -rf /var/lib/apt/lists/*

#RUN keytool -import -alias bbmx -keystore $JAVA_HOME/lib/security/cacerts -file /$PROJECT_NAME-$VERSION.jar/bbmx.cer -storepass changeit -noprompt

#RUN keytool -import -alias certificate -keystore $JAVA_HOME/lib/security/cacerts -file /$PROJECT_NAME-$VERSION.jar/certificate.pem -storepass changeit -noprompt

#RUN echo "$COMPILE"

#RUN echo "java $COMPILE-jar $PROJECT_NAME-$VERSION.jar"

#ENTRYPOINT ["java","-jar","$PROJECT_NAME-$VERSION.jar"]