# Introduction
This project is mainly about using Twitter REST API creates a Java application. This Java application allows user to create, delete and find the specific tweet. It uses MVC(Model–view–controller) design pattern to manage the code structure. This application helped me learned the MVC design pattern, Twitter REST API and Mockito Unit test framework.Also, I learned the class dependency and some basic idea on Spring framework. 

# Quick Start

### Setting Twitter REST API KEYS
This application need the several keys to process the request.
 ```
 export consumerKey=[]
 export consumerSecret=[]
 export accessToken=[]
 export tokenSecret=[]
 ```
### Maven
We already had the pom.xml file. So we use the mvn package to build the package.
`mvn package`

###Execution
After we have done the Maven step. We can go target folder and find the jar file.
```
java -jar twitter-1.0-SNAPSHOT.jar post|show|delete [options]  

Create tweet usage:
java -jar twitter-1.0-SNAPSHOT.jar post [txt] [lon:lat]

Show tweet usage:
java -jar twitter-1.0-SNAPSHOT.jar show [id] [filed1,filed2]

Delete tweet usage:
java -jar twitter-1.0-SNAPSHOT.jar delete [id_1,id_2...]
```
# Design
![](https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/blob/develop/core_java/twitter/asset/UML.png)

###TwitterCLIApp
This is the main class. In this component we create all other classes/component, accept the arguments that are passed by users and perform the execution.
###TwitterController
Controller accepts the arguments that are passed by the CLI App and do the parse operation then call the service component to execute the bushiness logic operation.
###TwitterService
Service component takes the all needed fields and performs the business logic operation for the application then call the data access object component.
###TwitterDao
DAO component initialises the corresponding HTTP request and pareses the HTTP response body JSON String to Tweet object.
###TwitterHttpHelper
HttpHelper generates the corresponding URI and also send the HTTP request by using Twitter REST API.

# Spring
We create a configuration file and set up the IoC container for the Spring framework. Then for each components we are using, we set the correct annotation for it.@Repository for TwitterDao, @Component TwitterHttpHelper and TwitterCLIApp, @Controller for TwitterController, @Service for TwitterService. 

# Models
For simplicity, we don't create all fields for a complete version of Tweet object. We use the Java POJO to represent it.
```
{
  "created_at" : "Thu Nov 28 20:11:33 +0000 2019",
  "id" : 1200145224103841792,
  "id_str" : "1200145224103841792",
  "text" : "test post",
  "entities" : {
    "hashtags" : [ ],
    "user_mentions" : [ ]
  },
  "retweet_count" : 0,
  "favorite_count" : 0,
  "favorited" : false,
  "retweeted" : false
}
```

# Improvements
1. When creating the tweet, we can let users enter more fields into Tweet object.
2. For showing tweet, we can add search by screen_name functionality.
3. We can implement the show specific fields functionality rather than show the complete Tweet.