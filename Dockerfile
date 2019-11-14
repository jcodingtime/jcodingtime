FROM openjdk:8

RUN apt-get update && apt-get install -y \
    javacc \
    git \
    junit

WORKDIR /usr/src/
RUN mkdir jcodingtime
WORKDIR /usr/src/jcodingtime
RUN git clone https://github.com/jcodingtime/tcc-jcodingtime.git