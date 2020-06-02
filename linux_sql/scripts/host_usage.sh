#!/bin/bash
#define variable
hostname_user="$1"
port_number="$2"
db_name="$3"
psql_user="$4"
psql_password="$5"

#export the password for enviornment variable
export PGPASSWORD="$psql_password"

hostname=$(hostname -f)

vmstat_out=`vmstat -t`
#usage
timestamp=$(date +"%F %T")

memory_free=$(echo "$vmstat_out" | awk '{print $4}' | grep -E '[0-9]')

cpu_idle=$(echo "$vmstat_out" | awk '{print $15}' | grep -E '[0-9]')

cpu_kernel=$(echo "$vmstat_out" | awk '{print $14}' | grep -E '[0-9]')

disk_io=$(vmstat -d | awk '{print $10}' | grep -E '[0-9]')

disk_available=$(df -BM / | awk '{print $4}' | grep -E '[0-9]' | tr -d "M")

insert_stmt="INSERT INTO host_usage (timestamp,host_id, memory_free,cpu_idle,cpu_kernel,disk_io,disk_available)
VALUES('${timestamp}',(select id from host_info WHERE host_info.hostname = '${hostname}'),'${memory_free}','${cpu_idle}','${cpu_kernel}','${disk_io}','${disk_available}');"
psql -h "$hostname_user" -p "$port_number" -U "$psql_user" -d "$db_name" -c "$insert_stmt"