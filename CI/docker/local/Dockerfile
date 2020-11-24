FROM strimzi/kafka:0.19.0-kafka-2.5.0
USER root:root

WORKDIR /opt/kafka/plugins/
RUN mkdir twitter-stream-connect
COPY target/twitter-stream-connect-1.0-jar-with-dependencies.jar ./twitter-stream-connect/twitter-stream-connect.jar
COPY config/twitter-stream-connect.properties ./twitter-stream-connect/twitter-stream-connect.properties
COPY config/connect-standalone.properties ./twitter-stream-connect/connect-standalone.properties

#VOLUME /twitter-stream-connect/config
#VOLUME /twitter-stream-connect/offsets

#ENV plugin.path="./target/"
#CMD ls -Lg ./twitter-stream-connect
CMD /opt/kafka/bin/connect-standalone.sh ./twitter-stream-connect/connect-standalone.properties ./twitter-stream-connect/twitter-stream-connect.properties