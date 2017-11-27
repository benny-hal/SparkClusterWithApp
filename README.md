# Spark-Excersise
An example of using a Docker image to create and submit a Spark App.

## Requirements
1. [Docker](https://docs.docker.com/engine/installation/) 1.10.0+ and [docker-compose](https://docs.docker.com/compose/) 1.6.0+
2. [SBT](http://www.scala-sbt.org/)

## Running
1. Create the docker images by executing the following command inside the ``sbt-application`` directory:

```sbt assembly```

2. Run the Spark cluster by executing the following command from the top directory:

```docker-compose up master worker```

This will run two images: 
- Spark master
- Spark worker

3. Run the Spark app by executing the following command from the top directory

```docker-compose up application```

This will run one image: 
- sbt-application (Spark job)