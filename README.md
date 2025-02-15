
# Task Manager

A simple demo application for managing your activities.


## Badges


[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


## Run Locally

Clone the project

```bash
  git clone https://github.com/DmitryV13/task-manager.git
```

First of all create container for DataBase.
Build Dockerfile in the root as follows

```bash
  cd "project-folder"
  docker build -t l3-db:1.0 .
```

Create network for container

```bash
  docker network create l3-network
```

Then create a container

```bash
  docker run \
  --name l3-db-cont \
  --network l3-network
  -p 5432:5432 \
  -e POSTGRES_DB=tdlist \
  -e POSTGRES_USER=admin \
  -e POSTGRES_PASSWORD=password \
  -v ~/volumes/dbs/TaskManager/postgresql/:/var/lib/postgresql/data/ \
  l3-db:1.0
```

Go to the front directory

```bash
  cd front
```

Install dependencies and build

```bash
  npm install
  npm run build
```

Start the server

```bash
  npm run start
```

Go to the back directory

```bash
  cd back
```

Build the project from IntelliJ IDEA or command line

```bash
  mvn package
```

Start the server

## Run in containers

Build Dockerfile in the front folder as follows

```bash
  docker build -t l3-front:1.0 .
```

Then create the container

```bash
  docker run --name l3-front-cont --network l3-network -p 9000:9060 l3-front:1.0
```

Build Dockerfile in the back folder as follows

```bash
  docker build -t l3-back:1.0 .
```

Then create the container

```bash
  docker run --name l3-back-cont --network l3-network l3-back:1.0
```

Now application is available on port :9060
