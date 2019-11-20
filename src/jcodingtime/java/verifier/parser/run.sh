#!/usr/bin/env bash

javacc jct.jj
javac *.java
#java Parser < grammar.txt