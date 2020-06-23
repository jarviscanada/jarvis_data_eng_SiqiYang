# Introduction
This application uses JDBC to build a connection between Java and Postgres SQL database. It implements CRUD(create, read, update, delete) operations by using the Data Access Object(DAO) design pattern. By implementing this application, I learned basic JDBC concepts, how to use JDBC to build a connection, and the Data access object design pattern. 

# ER Diagram
![](<https://github.com/jarviscanada/jarvis_data_eng_SiqiYang/tree/readme/core_java/jdbc/asset/ER_diagram.png>)

# Design Patterns
The Data Access Object (DAO) pattern is a structural pattern that allows us to isolate the application layer from the persistence layer using an abstract API.This abstract API is to hide from the application all the complexities involved in performing CRUD operations in the underlying storage mechanism. When we are dealing with centralized system or multiples table joins, DAO is the great choice.

The repository design pattern is an abstraction of a collection of objects. When we are dealing with a distributed system or the application is horizontally scalable, the repository design pattern is a good choice.

