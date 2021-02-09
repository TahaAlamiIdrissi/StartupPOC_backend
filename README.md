# StartupPOC 

A web application for time management  

The project is divided into back-end and front-end part  


[StartupPOC_backend](https://github.com/TahaAlamiIdrissi/StartupPOC_backend) : the back-end of the project using 
 
 - JAVA 8
 - SpringBoot
 
[StartupPOC_frontend](https://github.com/TahaAlamiIdrissi/StartupPOC_frontend) : the front-end of the project using 
 
 - Vuejs 2
 - Vuetify 
 
# StartupPOC_backend

This project builds rest service with springboot and implements a full backend api 

## Structure
The project contains the following folders: 

- src/main/java : contains the implementation of  different classes  of the project
- src/main/test : contains unit tests for the implemented classes
- src/main/ressources : contains the __application.properties__ where we can find the configuration of  datasource and mailing properties

## Packages 

## Domain
contains the different domain classes of the project .  
The following class diagram presents the different attributes of our entities and the relationships between them .
![](poc_class_diagram.png)

## Repository 
contains repository interface that extends JpaRepository for each domain 

## Service
contains service interface for each domain 

## ServiceImpl
contains classes that implement service interfaces , these classes are used to write the business logic code that include  store, retrieve, update and delete operations

## Config
contains the configuration class for spring security





 
# StartupPOC_frontend
 
 
 




