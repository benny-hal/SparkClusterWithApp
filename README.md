# Spark-Excersise
An example of using a Docker image to create and submit a Spark App.

## Requirements
1. [Docker](https://docs.docker.com/engine/installation/) 1.10.0+ and [docker-compose](https://docs.docker.com/compose/) 1.6.0+
2. [SBT](http://www.scala-sbt.org/)

## Running
1. Create docker image of the sbt-application by executing following command inside ``sbt-application`` directory:

```sbt docker```

2. Run the Spark cluster from the top directory:

```docker-compose -f docker-compose-spark.yml up```

This will run two images: 
- Spark master
- Spark worker

3. Run the Spark app from the top directory

```docker-compose -f docker-compose-app.yml up```

This will run one image: 
- sbt-application (Spark job)