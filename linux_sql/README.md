# Introduction
Cluster Monitor Agent is an internal tool that monitors the cluster resources such as hardware information, and some usage data for each network node which is connected by a switch.it uses two bash-scripts to collect the information from each host and store the data in Database.It helps the infrastructure team to make decision for the future planning. such as add/remove server. 

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
![](<https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/readme/linux_sql/assets/architecture.png>)

# Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`

Column Name | Contents
------------ | -------------
id| the id of the host.
hostname | the fully name of host
cpu_number | the number of cpu of host
cpu_architecture| System architecture of host.
cpu_model|CPU model of host.
cpu_mhz|  cpu clock rate of host.
L2_cache|  cache memory of host.
total_mem|  total memory of host.
timestamp|  date time when the data be collected.

- `host_usage`

Column Name | Contents
------------ | -------------
timestamp|datatime when data be collected
host_id| id of the host
memory_free| the free memory of host.
cpu_idle| the cpu idle percentage.
cpu_kernel| the percentage of cpu running the kernel
disk_io| the number of I/O disk
disk_available | the available amount of disk available.

## Scripts
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh: it is used to create the docker container using psql-image, and start and stop the container.
```
## used to create the psql container
./scripts/psql_docker.sh create db_username db_password

### used to start the ocntainer
./scripts/psql_docker.sh start

## used to stop the container
./scripts/psql_docker.sh stop
```
- host_info.sh: it is used to gather the information from the host and store into the host_info table.
```
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```
- host_usage.sh: it is used to gather the usage information from the host and store into the host_usage table.
```
bash scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
```
- crontab: it is used to run the host_usage script every 1 minutes.
```
crontab -e
* * * * * bash /home/centos/dev/jrvs/bootcamp/linux_sql/host_agent/scripts/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log
```
- queries.sql: used to produce the output of the problem 1.Group hosts by CPU number and sort by their memory size in descending order.
2.Average used memory in percentage over 5 minutes interval for each host.
```
psql -h localhost -U username -d host_agent -f sql/queries.sql
```

## Improvements 
Write at least three things you want to improve 
- We can modify the script to handle the hardware information update.
- Currently, we still have some commands that need to be entered by hand, we can generate those commands into scripts as well.
- Can create a user interface rather than execute these bash scripts and RDBMS files through terminal. 
```