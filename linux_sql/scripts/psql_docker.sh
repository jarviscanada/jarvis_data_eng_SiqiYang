#!/bin/bash
#define the needed variable
operation="$1"
username="$2"
password="$3"
#check if docker is running, if not running, start it.
systemctl status docker || systemctl start docker

#create the helper function for us to check if the container is created or not
check_container_created() {
if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" != 2 ]
then
  echo "the jrvs-psql not exist, create a container first"
  exit 1
fi
}

#check if the operation is create
if [ "$operation" == "create" ]
then
  # check if the docker container jrvs is create or not
  if [ "$(docker container ls -a -f name=jrvs-psql | wc -l)" == "2" ]
  then
    echo "jrvs-psql already exist"
    exit 1
  fi
  #check is we have the database password or username be given
  if [ $# -ne 3 ]
  then
    echo "missing the database name and/or password"
    exit 1
  fi
  #create the pgdata volume
  docker volume create pgdata
  #create the database name and password by using given username and psswd
  docker run --name jrvs-psql -e POSTGRES_PASSWORD=${password} -e POSTGRES_USER=${username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
  exit $?
fi

#check is the container created or not
check_container_created

#if the first argument is start. check if container exist and start it.
if [ "$operation" == "start" ]
then
  check_container_created
  docker container start jrvs-psql
  exit $?
fi

#if the first argument is stop. check if the container exist and stop it.
if [ "$operation" == "stop" ]
then
  check_container_created
  docker container stop jrvs-psql
  exit $?
fi

#if the first argument is invalid print error
if [ "$operation" != "create" ] && [ "$operation" != "start" ] && [ "$operation" != "stop" ]
then
  echo "invalid input please choose from create/start/stop"
  exit 1
fi