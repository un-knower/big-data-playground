FROM java:openjdk-8-jre

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get -y upgrade && apt-get install -y  \
    dnsutils \
    supervisor \
    wget \
 && rm -rf /var/lib/apt/lists/* \
 && apt-get clean

ARG SCALA_VERSION=2.12
ARG KAFKA_VERSION=1.1.0
ENV KAFKA_HOME /usr/local/kafka

RUN wget -q http://apache.mirror.anlx.net/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
      -O /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
 && tar -xzf /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -C /usr/local/ && \
    mv /usr/local/kafka_${SCALA_VERSION}-${KAFKA_VERSION} /usr/local/kafka/ && \
    rm /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz

CMD ["/usr/bin/supervisord"]
