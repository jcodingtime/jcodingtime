#!/usr/bin/env bash

javacc grammar.jj
javac *.java
java Parser < grammar.txt