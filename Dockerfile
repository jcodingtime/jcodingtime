FROM openjdk:8

RUN apt-get update && apt-get install -y \
    git \

WORKDIR /usr/src/
RUN mkdir com.github.jcodingtime
WORKDIR /usr/src/com.github.jcodingtime
RUN git clone https://github.com/com.github.jcodingtime/tcc-com.github.jcodingtime.git
WORKDIR /usr/src/com.github.jcodingtime/src/com.github.jcodingtime/scripts
RUN ./setup.sh
