FROM amazoncorretto:11

ENV PROJECT_NAME=BanBajio
ENV VERSION=2.5.8
ENV APP_PORT=8091
ENV COMPILE="-Xminf0.1 -Xmaxf0.3 -Xms128M -Xmx512M "

RUN mkdir /$PROJECT_NAME
WORKDIR /$PROJECT_NAME
VOLUME /$PROJECT_NAME
EXPOSE $APP_PORT $APP_PORT
EXPOSE 8091 8091
EXPOSE 80 8091
EXPOSE 443 8091
ADD target/$PROJECT_NAME-$VERSION.jar target/$PROJECT_NAME/$PROJECT_NAME-$VERSION.jar

ADD ./vmwnasistappqa.pfx /$PROJECT_NAME-$VERSION.jar/vmwnasistappqa.pfx
ADD ./bbmx.cer /$PROJECT_NAME-$VERSION.jar/bbmx.cer
ADD ./certificate.pem /$PROJECT_NAME-$VERSION.jar/certificate.pem

RUN yum update -y && yum install -y vim && rm -rf /var/lib/apt/lists/*

RUN keytool -import -alias bbmx -keystore $PROJECT_NAME-$VERSION.jar/lib/security/cacerts -file bbmx.cer -storepass changeit -noprompt

RUN keytool -import -alias certificate -keystore $PROJECT_NAME-$VERSION.jar/lib/security/cacerts -file certificate.pem -storepass changeit -noprompt

RUN echo "$COMPILE"

RUN echo "java $COMPILE-jar $PROJECT_NAME-$VERSION.jar"

ENTRYPOINT ["sh", "-c", "java -Xminf0.1 -Xmaxf0.3 -Xms128M -Xmx512M $COMPILE-jar $PROJECT_NAME-$VERSION.jar" ]


