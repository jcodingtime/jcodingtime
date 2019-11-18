FROM openjdk:8

RUN apt-get update && apt-get install -y \
    git \

WORKDIR /usr/src/
RUN mkdir jcodingtime
WORKDIR /usr/src/jcodingtime
RUN git clone https://github.com/jcodingtime/tcc-jcodingtime.git
WORKDIR /usr/src/jcodingtime/src/jcodingtime/scripts
RUN ./setup.sh