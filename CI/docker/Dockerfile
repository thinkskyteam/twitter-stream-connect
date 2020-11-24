FROM confluentinc/cp-kafka-connect:latest

USER root:root

RUN yum -qy update \
 && yum -qy install wget unzip make gcc \
 && yum -y install https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm \
 && yum install jq -y


WORKDIR /usr/share/confluent-hub-components

#RUN mkdir twitter-stream-connect
ARG ARTIFACT_NAME

COPY maven/components/packages/${ARTIFACT_NAME}.zip .
RUN unzip ./${ARTIFACT_NAME}.zip
COPY config/twitter-stream-connect.properties ./${ARTIFACT_NAME}/twitter-stream-connect.properties

ENV CONNECT_PLUGIN_PATH="/usr/share/java,/usr/share/confluent-hub-components"

RUN confluent-hub install --no-prompt confluentinc/kafka-connect-elasticsearch:10.0.0
#ENTRYPOINT /etc/confluent/docker/run & sleep infinity