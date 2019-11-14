#!/usr/bin/env bash

echo "Installing: javacc and junit


# Install javacc:
# Read a grammar specification and converts it to a
# Java program that can recognize matches to the grammar
#
echo "Install javacc: starting"
sudo apt-get install javacc=5.0-5 -y

echo "Install javacc: DONE"

# Install junit:
# Framework
#
echo "Install junit: starting"
sudo apt-get install junit=3.8.2-8build1 -y

echo "Install junit: DONE"