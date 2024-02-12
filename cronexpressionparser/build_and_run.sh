#!/bin/bash

# Build Maven project
mvn clean package

# Check if Maven build was successful
if [ $? -eq 0 ]; then
    # Run JAR command
    java -jar ./target/cronexpressionparser-1.0-SNAPSHOT.jar "$1"
else
    echo "Maven build failed. Exiting."
    exit 1
fi
