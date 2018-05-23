#!/bin/bash

echo "Generating the artifact"
./mvnw clean package

echo "Building the docker image"
docker build -t vallim/music-recommendation-api .

echo "Starting the application"
docker run --name recommendation-app1 -d -p 8080:8080 vallim/music-recommendation-api

success=200

while :
do
	response=$(curl --write-out %{http_code} --silent --output /dev/null http://localhost:8080/health)

	if [ $response -eq $success ];then
	  break
	fi
done

echo "Started the application successfully"