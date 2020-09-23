# Introduction
This project is about using the previously developed trading application and migrate it to the AWS platform. It also uses Jenkins to
create the automated deployment process for the trading application. The trading application uses Three-Tier Architecture as it's overall structure. It divides the whole application into the client tier, application tier, and database tier. In this cloud-DevOps project, it uses EC2 instance to handle the application tier. It makes use of the RDS database as the database tier to persist the data. Also, It utilizes the load balancer to distribute the income traffic and uses the auto-scaling group to ensure the scalability. In addition, Elastic beanstalk is used to simplify the deployment process and provide different environments. Furthermore, 
 Jenkins is used to creating the CI/CD pipeline. Jenkins pulls the new version from GitHub, builds, and deploys the Elastic beanstalk application. 

# Application Cloud Architecture
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/cloud_devops/draw_io/cloud.png).

The load balancer is used to handle the large incoming traffic. It will distribute the incoming request to the existing EC2 instance and make sure all the EC2 instance in the current auto-scaling group has the balance. Also, It sends the health
request to each of the EC2 instances and make sure not forwarding the further request to the unhealthy EC2 instance.
The auto-scaling group is used to make sure the elastic of the application, it will cooperate with the load balancer and scale in and out the instance.

# Deployment Environments
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/cloud_devops/draw_io/elastic.png).

Elastic Beanstalk is used to simplify the deployment process. By using Elastic beanstalk, there are two environments created. 
The development environment and production environment. 
The development environment represents the under development version and the production environment represents the production-ready version of the application.


# Jenkins Server
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/cloud_devops/draw_io/jenkins.png).

# Jenkins CI/CD pipeline
To create the CI/CD pipeline using Jenkins, an Ubuntu EC2 instance was created. Then in this EC2 instance,
I installed some plugins including the NGINX, Jenkins, and Maven then manually added the JDK, Maven, and Git to Jenkins.
NGINX reverse proxy was created and used to forwarding the HTTP request to the Jenkins Server. 
Also, I added the Jenkinsfile to define the process of the CI/CD pipeline. First, We update the source code
from GitHub and then Jenkins uses the source code to build the application and pass the application to let Elastic beanstalk deploy the application.


# Improvements
1. Let the security group rules more strict.
2. Add more environments such as some feature environments.
3. Test more larger traffic to make sure the load balancer's performance.