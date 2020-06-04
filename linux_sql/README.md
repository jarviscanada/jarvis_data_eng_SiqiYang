# Introduction
Cluster Monitor Agent is an internal tool that monitors the cluster resources such as hardware information and some usage for each network node which is connected by a switch.it uses two bash-scipts to collect the information from each host and store the data in database.It helps the infrastructure team to make decision for the future planning. such as add/remove server. 

# Quick Start
Use markdown code block for your quick start commands
- Start a psql instance using psql_docker.sh
``./scripts/psql_docker.sh start|stop|create [db_username][db_password]``
- Create tables using ddl.sql
`` psql -h localhost -U username -d host_agent -f ./ddl.sql``
- Insert hardware specs data into the db using host_info.sh
``./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password``
- Insert hardware usage data into the db using host_usage.sh
``scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password``
- Crontab setup
```
crontab -e
* * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.lo
```

# Architecture Diagram
Untitled Diagram.png

# Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`

- `host_usage`

## Scripts
Shell script descirption and usage (use markdown code block for script usage)
- psql_docker.sh
- host_info.sh
- host_usage.sh
- crontab
- queries.sql (describe what business problem you are trying to resolve)

## Improvements 
Write at least three things you want to improve 
e.g. 
- handle hardware update 
- blah
- blah
```