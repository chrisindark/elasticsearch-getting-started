# elasticsearch-getting-started

## run ES on docker

docker network ls

docker network create -d bridge elastic

docker-compose -f elasticsearch-docker-compose.yml up --build --force-recreate

docker exec -it es01 bash

bin/elasticsearch-setup-passwords auto

docker-compose -f kibana-docker-compose.yml up --build --force-recreate

docker exec -it kibana01 bash

docker network ls
docker container ls
docker network inspect bridge

### ES cluster for production

Nodes:
Minimum of 3 Nodes: For high availability and fault tolerance, it's recommended to have a minimum of 3 master-eligible nodes in your Elasticsearch cluster. This helps prevent split-brain scenarios and ensures cluster stability.
Data Nodes: Additional data nodes can be added based on your data volume and performance requirements. Data nodes handle indexing and search operations and store the actual data.
Ingest Nodes: If you have heavy data ingestion (indexing) workloads, consider adding dedicated ingest nodes to offload indexing tasks from data nodes.

Shards:
Primary and Replica Shards: Elasticsearch uses primary shards for indexing data and replica shards for redundancy and high availability. Each index can have multiple primary shards (usually between 1 and 5) and optional replica shards (typically 1 or 2 replicas).
Number of Shards per Node: Avoid having too many shards per node, as it can lead to overhead and resource contention. A common recommendation is to limit the number of shards per node to a few dozen (e.g., 20-50) primary shards, depending on the node's resources (CPU, memory, disk).

Cluster Size and Scaling:
Horizontal Scaling: As your data volume or query load grows, you can horizontally scale your Elasticsearch cluster by adding more nodes. This allows you to distribute the workload and maintain performance.
Vertical Scaling: You can also vertically scale individual nodes by increasing their CPU, memory, or disk resources to handle increased demand. However, there are practical limits to how much you can vertically scale a single node.

Data Modeling and Indexing:
Optimize Data Modeling: Design efficient index mappings and data models to minimize the number of shards and ensure optimal performance for search and aggregation queries.
Bulk Indexing: Use bulk indexing techniques for efficient data ingestion, especially when indexing large volumes of data. Batch indexing operations can improve throughput and reduce indexing overhead.


## ES Cluster with multiple nodes and shards

docker exec -it es01 bash

cd config
nano elasticsearch-1.yml
```
cluster.name: "docker-cluster"
node.name: node-1
network.host: 0.0.0.0
http.port: 9201
transport.port: 9301
discovery.type: multi-node
discovery.seed_hosts:
  - es02:9302
  - es03:9303
cluster.initial_master_nodes:
  - node-1
```

nano jvm.options
 -Xms250m
 -Xmx250m

run other nodes in different ports and specify the seed hosts and master nodes accordingly

###
create an index and add some data into it

hit:
http://localhost:9201/_cat/indices
http://localhost:9201/test
http://localhost:9201/test/_search
