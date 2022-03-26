docker-compose -f elasticsearch-docker-compose.yml up --build --force-recreate

docker exec -it es01 bash

bin/elasticsearch-setup-passwords auto

docker-compose -f kibana-docker-compose.yml up --build --force-recreate

docker exec -it kibana01 bash

docker network ls
docker container ls
docker network inspect bridge
