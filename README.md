# Alankar Apte

Multithreading in spring boot app using CompletableFuture
How thread pool works in executor framework
CompletableFuture introduced in JDK 8
It provide easy way to write Async/non-blocking/multithreaded code

# Quick

Here we will read data from CSV file & persist in DB
Then using multithreaded code we will used that data 
So this demo project on how thread pool concept work

-We are configuring taskExecutor for Async 
-using H2 in-memory DB
-generated dummy data from https://www.mockaroo.com/
-accepting csv files (key in postman 'files' cause we used MultipartFile)
-extracting data from csv and persisting it in H2 db
-All this above flow achieved using @Async


# dependencies 
H2
Web
Data JPA
DevTools
Lombok - 
	If you use STS. You must have Lombok installed in your Eclipse by running lombok-xyz.jar
	Please Try the Following the Steps:
    		-Include pom in Maven .
    		-Exit/Shutdown STS
   		-Find lombok Jar in ~/.m2/repository/org/projectlombok/lombok/version.x
   	 	-From Command Prompt/Shell java -jar lombok-1.x.y.jar

# Generate CSV/Xml etc files dynamically
* Site: https://www.mockaroo.com/
A free test data generator and API mocking tool - Mockaroo lets you create custom CSV, JSON, SQL, and Excel datasets to test and demo your software.

# Annotation
@EnableAsync	//it will inform spring framework to run your task in background using thread pool concept


# Steps to impliments + Important things to take from this project are as 

1. Create config class &  configure bean of Executor to ThreadPoolTaskExecutor
NOTE: Even if you not configure this Executor it won't create this 'ThreadPoolTaskExecutor', Instead it will create 'SimpleAsyncTaskExecutor'

@Configuration
@EnableAsync	//it will inform spring framework to run your task in background using thread pool concept
public class AsyncConfig{}

2. You can follow implimentation 'UserService's saveUser(..) method

3. you can follow implimentation of 'UserController's findAllUser(..) method 

4. VVIP step which will actually demonstrate multithreading - implimentation of 'UserController's findAllUsersByMultiThread(..) method
	-call '/allUsersByMultiThread' endpoint and observe the logs 
	-we have already configure thread pool capacity = 2
	-and we are calling  'userService.findAllUser()' method 3 time here
	-so it get execute parallelly by that two threads
	-so on logs we are printing thread name so 
output on log will be like below :-
2021-01-15 15:40:16.702  INFO 22300 --- [   userThread-2] c.a.executor.api.service.UserService     : Get list of user by userThread-2 
2021-01-15 15:40:16.702  INFO 22300 --- [   userThread-2] c.a.executor.api.service.UserService     : Get list of user by userThread-1 
2021-01-15 15:40:16.702  INFO 22300 --- [   userThread-2] c.a.executor.api.service.UserService     : Get list of user by userThread-2 






----------------------------------------------------------------------------------------------------


# default readme content


# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.alankar-practice.executor.api' is invalid and this project uses 'com.alankarpractice.executor.api' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.1/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.1/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

