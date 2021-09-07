Starting with onboarding tutorial
09.08.2021

# Preparation

- installed Ubuntu
- installed Java 16 and set JAVA_HOME and PATH
- installed IntelliJ IDEA
- installed Docker Desktop
- installed Maven and set MAVEN_HOME AND PATH
- installed postman
- created git repository (https://github.com/RaphaelKunis/onboarding.git)
- installed mysql Workbench
- activate wsl (https://docs.microsoft.com/en-us/windows/wsl/install-win10)
	WSL 2 needed to use docker desktop in linux subsystem
- [x] all done
- Additional
	- install _Windows Terminal_ https://www.microsoft.com/de-de/p/windows-terminal/9n0dx20hk701?rtc=1#activetab=pivot:overviewtab

# Steps
## Step 1)
- new folder spring-boot-hello in git repository
- https://start.spring.io/
- [x] example is running 

# Step 2)
- docker pull mysql
- docker run --name mysql_docker -e MYSQL_ROOT_PASSWORD=<PWD> -p 3306:3306 -d mysql
- docker ps
- [x] docker mysql is running 

#Step 3) 
- mysql spring boot app with mysql connection
- new folder spring-boot-mysql in git repository (web + mysql + jpa + spring security)
  - hint: when spring security is chosen for spring Initializr then a login page is presented
    - use "user" as username and the password given in the cmd-line as login data in browser
    - for tests with curl uncomment the dependency in pom.xml at first as it has some difficulties to manage
- empty project from https://start.spring.io (https://spring.io/guides/gs/accessing-data-mysql/)
- ![images/img_step3.png](images/img_step3.png)
- started docker container with mysql (see step 2)
  - added account with mysql workbench (springuser, ThePassword1234) and created database db_example;
    - see [sql_create_script.sql](sql_create_script.sql)
- added GetRequest for user by id
  - [ ] todo: check valid int as param and ?format null output?
- added Service class
  - [ ] todo: implement addUser method in UserService
    - add better result
- added input validation
  - [x] validation with bean annotation
    - information found on [https://www.baeldung.com/javax-validation](https://www.baeldung.com/spring-service-layer-validation) and [https://www.baeldung.com/spring-service-layer-validation](https://www.baeldung.com/spring-service-layer-validation)
  - additional validation
    - [ ] todo
    