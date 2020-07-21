Table of contents
* [Introduction](#Introduction)
* [Quick Start](#Quick Start)
* [Architecture](#Architecture)
* [REST API Usage](#REST API Usage)
* [Docker Deployment](#Docker Deployment)
* [Improvements](#Improvements)

# Introduction
This project is an online stock trading simulation REST API that supports CRUD operations on trader, account, security orders, and quotes. 
Front-end developers can utilize this REST API and combine it with the front-end application to achieve a complete online stock trading simulate application for various platforms.
 Also, the application can be consumed by using Swagger UI or postman. This application is a microservice which is implemented by Java along with SpringBoot.
  It utilizes the MVC design pattern and Three-Tier architecture to manage the overall code structure. 
  It retrieves real market data by using IEX cloud API and stores the market data into the PostgreSQL database to persist the data. 
  It also makes use of the Mockito framework and integration test to ensure it's quality.



# Quick Start
- Prequiresites: Docker, CentOS 7
- Docker scritps with description
	- build PSQL image
	```
	 cd ./springboot/psql
  docker build -t trading-psl.
	```
	- build trading-app image
	```
	 cd ./springboot/
  docker build -t trading-app .
	```
  - create docker network
  ```
  sudo docker network create trading-net
  ```
  - start containers
  ```
  docker run --name trading-psql-dev \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_DB=jrvstrading \
  -e POSTGRES_USER=postgres \
  --network trading-net \
  -d -p 5432:5432 trading-psql
  
  #verify the docker container.
  docker ps
  
  #set IEX credential
  IEX_PUB_TOKEN="your_token"
  #start trading-app container which is attached to the trading-net docker network
  docker run --name trading-app-dev \
  -e "PSQL_URL=jdbc:postgresql://trading-psql-dev:5432/jrvstrading" \
  -e "PSQL_USER=postgres" \
  -e "PSQL_PASSWORD=password" \
  -e "IEX_PUB_TOKEN=${IEX_PUB_TOKEN}" \
  --network trading-net \
  -p 8080:8080 -t trading-app
  
  #list running containers
  # you should see two running docker containers
  docker container ls
  ```
- After the above steps, we can using Swagger UI to verify the application.
The URL for the application is [http://localhost:8080/swagger-ui.html#/](http://localhost:8080/swagger-ui.html#/.).
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/core_java/twitter/asset/UML.png)

# Architecture
- Draw a component diagram which contains controllers, services, DAOs, psql, IEX Cloud, WebServlet/Tomcat, HTTP client, and SpringBoot. 
- briefly explain the following components and services (3-5 sentences for each)
  - Controller layer (e.g. handles user requests....)
  - Service layer
  - DAO layer
  - SpringBoot: webservlet/TomCat and IoC
  - PSQL and IEX

# REST API Usage
## Swagger
What's swagger (1-2 sentences, you can copy from swagger docs). Why are we using it or who will benefit from it?
## Quote Controller
- High-level description for this controller. Where is market data coming from (IEX) and how did you cache the quote data (PSQL). Briefly talk about data from within your app
- briefly explain each endpoint
  e.g.
  - GET `/quote/dailyList`: list all securities that are available to trading in this trading system blah..blah..
## Trader Controller
- High-level description for trader controller (e.g. it can manage trader and account information. it can deposit and withdraw fund from a given account)
- briefly explain each endpoint
##Order Controller
- High-level description for this controller.
- briefly explain each endpoint
## App controller
- briefly explain each endpoint
## Optional(Dashboard controller)
- High-level description for this controller.
- briefly explain each endpoint

# Docker Deployment
- docker diagram including images, containers, network, and docker hub
e.g. https://www.notion.so/jarviscanada/Dockerize-Trading-App-fc8c8f4167ad46089099fd0d31e3855d#6f8912f9438e4e61b91fe57f8ef896e0
- describe each image in details (e.g. how psql initialize tables)

# Improvements
If you have more time, what would you improve?
- at least 5 improvements