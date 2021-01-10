# Cake Manager with SpringBoot
## Prerequisite Software
 Install Java 1.8, Maven, git, docker.
 
## Check out the code 
 Using git: `git clone https://github.com/gfengster/cake-manager.git`<br>
 Download zip: `https://github.com/gfengster/cake-manager/archive/main.zip`

## Build and run in local machine
1. Test, build and pack the project
In a terminal, change path to the project.<br>
 `mvn clean test package`
2. Run the application<br>
`java -jar ./target/cake-manager.jar`

## Build and run docker in local machine
1. Test, build and pack the project
In a terminal, change path to the project.<br>
 `mvn clean test package`
2. Build docker image<br>
`docker build --tag cake-manager:thymeleaf .`
3. Run a container<br>
`docker run -p 8080:8080 -p 18080:18080 cake-manager` 

## CI/CD with GitHub
The project source code is hosted in GitHub. CI/CD has been configured in workflows.
Changing code could trigger CI workflow, which test, build the project. If succeeded, docker image will be created and deployed to [DockerHub](https://hub.docker.com).<br>
CI/CD could be triggered manually.
1. The auto built docker image location<br>
[https://hub.docker.com/repository/docker/gfengster/cake-manager](https://hub.docker.com/repository/docker/gfengster/cake-manager)
2. Pull docker image built in GitHub from DockerHub<br>
`docker pull registry.hub.docker.com/gfengster/cake-manager:thymeleaf`
3. Create a container and run the application<br>
`docker run -p 8080:8080 -p 18080:18080 gfengster/cake-manager`

## Using the application
Note: Before run the application make sure port 8080, 18080 are free.
1. List all cakes in table with browser<br>
[http://localhost:8080](http://localhost:8080)
2. Retrieve all cakes in JSON format<br>
[http://localhost:8080/cakes](http://localhost:8080/cakes)
3. Retrieve a cake with id in JSON format<br>
`http://localhost:8080/cakes/{id}`<br>
For example<br>
[http://localhost:8080/cakes/2](http://localhost:8080/cakes/2)
4. Create a cake with browser<br>
[http://www.localhost:8080/create](http://www.localhost:8080/create)
5. Create a cake with curl in terminal<br>
`echo '{"title":"GFeng cake","desc":"My favourite cake is self made","image":"https://content.sponge.co.uk/sponge-heroes/_conceptHeroWide1x/Vegan-main-concpets2.jpg"}' | curl -X POST -d @-  http://localhost:8080/cakes --header "Content-Type:application/json"`
6. Check the created cake in browser<br>
[http://localhost:8080](http://localhost:8080)
7. Cakes list can be viewed from database directly in browser if the application run in local machine<br>
[http://localhost:8080/h2[(http://localhost:8080/h2)
8. View SpringBoot actuator, which shows application internal information.<br>
[http://localhost:18080/actuator/](http://localhost:18080/actuator/)<br>
[http://localhost:18080/actuator/health](http://localhost:18080/actuator/health)<br>
[http://localhost:18080/actuator/info](http://localhost:18080/actuator/info)
