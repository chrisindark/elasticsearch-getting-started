docker-compose -f docker-compose.yml up --build --force-recreate

docker-compose -f kibana-docker-compose.yml up --build --force-recreate

docker exec -it kibana01 bash

docker network ls
docker container ls
docker network inspect bridge
