# Introduction
This project is mainly about using Twitter REST API creates a Java application. This Java application allows user to create, delete and find the specific tweet. It uses MVC(Model–view–controller) design pattern to manage the code structure. This application helped me learned the MVC design pattern, Twitter REST API and Mockito Unit test framework.Also, I learned the class dependency and some basic idea on Spring framework. 

# Quick Start
- how to package your app using mvn
- how to run your app and explain option
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

# Spring
- How you managed the dependencies using Spring?


# Models
Talk about tweet model

# Improvements
- at least three improvements