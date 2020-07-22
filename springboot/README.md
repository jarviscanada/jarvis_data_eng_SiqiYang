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
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/springboot/assets/SwaggerUI.png).

# Architecture
- ![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/springboot/assets/structure.png).
##### Controller layer:
It handles the user input maps the corresponding request path and call the service layer to handle later operations.
##### Service layer:
It handles the bushiness logic and validate the inputs that are passed from controller layers. It invokes the error handling as well as the exception
 then call the DAO layer to perform the actually data manipulation.
##### DAO layer:
It serves for the service layer and performs the actual data manipulation. It retrieves data from the IEX cloud and 
 store any changed data to the PSQL.
##### SpringBoot:
SpringBoot provides the Inversion of control principle to the whole project. It helps to manage the dependencies between each 
components. Meanwhile it uses the embedded WebServlet/Tomcat to help us manage and map the entry point for the application.
##### PSQL and IEX:
PSQL provides the data storage platform to store the data. IEX cloud is used to retrieve and update the real market data.


# REST API Usage
## Swagger
The Swagger UI is an open source project to visually render documentation for an API defined with the OpenAPI (Swagger) Specification. Swagger UI lets we visualize and interact with the API’s resources without having any of the implementation logic in place, making it easy for back end implementation and client side consumption.
## Quote Controller
- It accepts the user input and perform the actions such as retrieves data from IEX cloud and stores it as 
IEX quote object, create quote objects for the PSQL data , update the quote in PSQL database.
  - GET `/quote/dailyList`: list all securities that are available to trading in this trading system.
  - PUT `/quote/iexMarketData`: update all the quote in the database and synchronize the data with IEX cloud statistic.
  - PUT `/quote`: save the quote into the trading system.
  - POST `/quote/tickerId/{tickerId}`: save the quote with the given ticker into the trading system.
  - GET `/quote/iex/ticker/{ticker}`: show the specific quote's details with the give ticker in IEX cloud.
## Trader Controller
- It manages the trader and it's account in the trading system. Such as creates a new trader, add fund or deposit fund into specific trader's account.
  - PUT `/trader/withdraw/traderId/{traderId}/amount/{amount}`: withdraw certain amount of fund from the trader's account.
  - PUT `/trader/deposit/traderId/{traderId}/amount/{amount}`: deposit certain amount of fund into trader's account.
  - DELETE `/trader/traderId/{traderId}`: delete a trader and it's account when it's fund is zero and no open position.
  - POST `/trader`: create a trader and a account with a given trader object given in the request body.
  - POST `/trader/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}`: create a trader and it's account with the given information.
## Order Controller
- It submits a security order into the trading system with the given market order object.
  -POST `/order/marketOrder`: create a security order with the given market order object into trading system's PSQL database.

## Dashboard controller
- It generates the trader profile and specification by using the trader's ID.
  - GET `/dashboard/profile/traderId/{traderId}`: show the trader profile by it's ID.
  - GET `/dashboard/portfolio/traderId/{traderId}`: show the trader portfolio by it's ID.

# Docker Deployment
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/springboot/assets/docker.png).
 - For the trading PSQL image, we build it base on the official Postgres docker image `postgres:9.6-alpine`.
  We copy the two sql files to the `/docker-entrypoint-initdb.d/` and these two sql files help us to initialize the database and tables in that database.
 - For the trading-app image, we build it base on the `openjdk:8-alpine` and `maven:3.6-jdk-8-slim` official images. In the Dockerfile, we copy the pom.xml and src files to the corresponding folder then let maven to 
 build it and clean the package. Then we copy the .jar file to the corresponding path and run with this jar file.

# Improvements
If you have more time, what would you improve?
- We can build a front-end application to consume this back-end api.
- Add authentication feature for each withdraw/deposit operation.
- Let quotes in the PSQL database automatically update and consist with the real data in IEX cloud.
- Let quote to store more information.
- Allow one trader having multiple accounts.