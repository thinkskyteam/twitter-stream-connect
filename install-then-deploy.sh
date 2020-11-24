#!/usr/bin/env bash

echo '================='
echo 'Run install'
./just-install.sh

echo '================='
echo 'Deploying on Cluster'
./just-deploy.sh
