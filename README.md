# Cake Manager with SpringBoot
1. Install Java 1.8, Maven, git, docker.
2. Check out the code 
<br><code>`git clone https://github.com/gfengster/spring-boot-sales.git`</code>
4. Compile the project
In a terminal, change path to the project.
<br><code>`mvn clean package`</code>
5. Run the project
<br><code>`java -jar ./target/demo-0.0.1-SNAPSHOT.jar`</code>
6. In web browser
<br>http://localhost:8080
7. In menu panel
  - Stock -- shows the current level
  - Sale -- can submit a sale, and result the sale status.
  - Sale History -- shows all sale event in history
  - Rule -- shows the sale rule on each product.
8.Data in database
H2 database with memory mode is used. Tables could be viewed via
<br>http://localhost:8080/h2
<br>jdbc url is "jdbc:h2:mem:test"

## H2 Run the demo in a docker container
1. Install docker, Java, Maven
2. Check out the project as above step 3.
4. Build project
<br><code>`mvn clean package`</code>
3. Build image
<br><code>`docker build --tag gfsaledemo:latest .`</code>
4. Run a container
<br><code>`docker run -p 8080:8080 gfsaledemo`</code>
