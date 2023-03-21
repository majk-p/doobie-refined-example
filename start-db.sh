#!/bin/bash
NAME=refined-doobie-example
PASSWORD=postgres
docker rm -f $NAME
docker run -d -p 5432:5432 --name $NAME -e POSTGRES_PASSWORD=$PASSWORD postgres
sleep 5
docker exec -i $NAME psql -U postgres <<< $(cat ./init.sql)
