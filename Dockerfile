FROM openjdk:8

RUN apt-get update && apt-get install -y \
    javacc \
    git

COPY . /usr/src/jcodingtime/tcc-jcodingtime
WORKDIR /usr/src/jcodingtime/tcc-jcodingtime
RUN javacc grammar.jj && javac *.java
CMD ["java", "Parser", "<", "input.txt"]
