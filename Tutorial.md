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
- new folder spring-boot-mysql in git repository
- empty project from https://start.spring.io (https://spring.io/guides/gs/relational-data-access/)
- ![images/img_step3.png](images/img_step3.png)
- 
- 