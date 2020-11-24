#!/usr/bin/env bash

echo '================='
echo 'Run maven install'
mvn clean install


echo '================='
echo 'Build docker image'
mvn fabric8:build


echo '================='
echo 'Push docker image'
mvn fabric8:push

