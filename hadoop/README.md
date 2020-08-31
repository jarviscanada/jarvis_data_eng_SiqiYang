* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop-Cluster)
* [Hive Project](#Hive-Project)
* [Improvements](#Improvements)

# Introduction
This project is mainly about helping data analytics team to process data using 
Apache Hadoop and evaluate different tools and components of Hadoop. It evaluated the 
 main components of Hadoop including  including MapReduce, HDFS, and YARN. The Hadoop cluster
 is provisioned by Google Cloud Platform. I solved some business problems by using Apache Hive
 and using Zeppelin Notebook as the web-based notebook to record the HiveQL queries and evaluate the output.
# Hadoop Cluster

![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/hadoop/assets/cluster.png)
### Zeppelin
Apache Zeppelin interpreter concept allows any language/data-processing-backend to be plugged into Zeppelin. It is a web-based notebook for us to 
run Hive queries and visualize the output data.
### HDFS
Hadoop Distributed File System (HDFS) is a distributed file system designed to run on commodity hardware.
It uses namenode persists metadata of the data set. It split the actual data set and stores the replicate in several data nodes.
### YARN
 YARN helps to open up Hadoop by allowing to process and run data for batch processing, stream processing, interactive processing, and graph processing which are stored in HDFS.
 The Resource manager in master node is the master daemon of YARN and is responsible for resource assignment and management among all the applications. Whenever it receives a processing request, it forwards it to the corresponding node manager
  and allocates resources for the completion of the request accordingly.
 The node manager in each worker node takes care of individual nodes on the Hadoop cluster and manages application and workflow and that particular node. 
 Its primary job is to keep up with the Node Manager. 
### Hive
Apache Hive is a data warehouse software project built on top of Apache Hadoop for providing data query and analysis. 
Hive gives an SQL-like interface to query data stored in various databases and file systems that integrate with Hadoop.
It has the metastore as it's component which is the central repository of Hive metadata. 
The metastore is divided into two pieces: a service and the backing store for the data in RDBMS.

# Hive Project
This project does some experiments on querying data in some different ways and compare different approach's performance.
It compared the performance when we store the data in remote location versus when we store the data in HDFS.
It evaluated the runtime performance when we use different way to write query HiveQL vs Spark vs bash script.
It Also explored the performance increasing when we use partition and columnar file to store data in tables.
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/hadoop/assets/notebook.png)


# Improvements
- use a larger data set to see the bigger difference on using bash script and using HiveQL to process the data.
- have more worker node to reduce the run time of the experiments.
- add more data set and do more experiments on some of the comparisons to make the conclusion more explicit.
