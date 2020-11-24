#!/usr/bin/env bash

echo '================='
echo 'Delete yaml(s)'
rm ./k8s.yaml


echo '================='
echo 'Apply kustomize on yaml(s)'
#echo 'kubectl apply -k CI/k8s/overlays/dev'
kustomize build CI/k8s/overlays/dev > ./k8s.yaml

echo '================='
echo 'Apply on K8s cluster'
kubectl apply -f ./k8s.yaml
echo '================='
echo 'end'

