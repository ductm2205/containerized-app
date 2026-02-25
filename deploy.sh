#!/bin/bash
set -e

cd /opt/app
git pull origin main

export IMAGE_TAG=${IMAGE_TAG}


SECRET=$(aws secretsmanager get-secret-value \
  --secret-id prod/db/mysql \
  --query SecretString \
  --output text)

export DB_HOST=$(echo $SECRET | jq -r .host)
export DB_PORT=$(echo $SECRET | jq -r .port)
export DB_NAME=$(echo $SECRET | jq -r .dbname)
export DB_USER=$(echo $SECRET | jq -r .username)
export DB_PASSWORD=$(echo $SECRET | jq -r .password)

docker compose pull
docker compose up -d