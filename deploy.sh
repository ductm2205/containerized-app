#!/bin/bash
set -e

SECRET_JSON=$(aws secretsmanager get-secret-value \
  --secret-id dev/db \
  --query SecretString \
  --output text)

cat <<EOF > .env
DB_HOST=$(echo $SECRET_JSON | jq -r .host)
DB_PORT=$(echo $SECRET_JSON | jq -r .port)
DB_NAME=$(echo $SECRET_JSON | jq -r .dbname)
DB_USER=$(echo $SECRET_JSON | jq -r .username)
DB_PASSWORD=$(echo $SECRET_JSON | jq -r .password)
EOF

docker compose pull
docker compose up -d