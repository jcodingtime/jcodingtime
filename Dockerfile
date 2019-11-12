FROM openjdk:8

RUN apt-get update && apt-get install -y \
    javacc \
    git

WORKDIR /usr/src/
RUN mkdir jcodingtime
WORKDIR /usr/src/jcodingtime
RUN git clone https://github.com/jcodingtime/tcc-jcodingtime.git
WORKDIR /usr/src/jcodingtime/tcc-jcodingtime
RUN javacc grammar.jj 
RUN javac *.java
CMD ["java", "Parser", "<", "input.txt"]
