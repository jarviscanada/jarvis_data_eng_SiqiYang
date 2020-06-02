#!/bin/bash
#define the variable
hostname_user="$1"
port_number="$2"
db_name="$3"
psql_user="$4"
psql_password="$5"

#export the password for enviornment variable
export PGPASSWORD="$psql_password"


lscpu_out=`lscpu`

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

hostname=$(hostname -f)

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

cpu_architecture=$(echo "$lscpu_out" | egrep "Architecture" | awk '{print $2}'| xargs)

cpu_model=$(echo "$lscpu_out"  | egrep "Model name" | awk -F':' '{print $2}' | xargs)

cpu_mhz=$(echo "$lscpu_out"  | egrep "CPU MHz" | awk -F':' '{print $2}' | xargs)

L2_cache=$(echo "$lscpu_out"  | egrep "L2 cache" | awk -F ":" '{print $2}'| tr -d 'K' | xargs)

total_mem=$(echo $(grep MemTotal /proc/meminfo) | awk -F": " '{print $2}'| tr -d "kB")

timestamp=$(date +"%F %T")

insert_stmt="INSERT INTO host_info (hostname, cpu_number,cpu_architecture,cpu_model,cpu_mhz,L2_cache,total_mem,timestamp)
VALUES('${hostname}',${cpu_number},'${cpu_architecture}','${cpu_model}','${cpu_mhz}','${L2_cache}','${total_mem}','${timestamp}');"
psql -h "$hostname_user" -p "$port_number" -U "$psql_user" -d "$db_name" -c "$insert_stmt"