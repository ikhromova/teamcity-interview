# Jetbrains-TeamCity-QAA-Interview

# Task
Let’s assume you just joined the TeamCity team. Your goal is to introduce automated testing to improve the quality of the product and the development process. At first, you will need to come up with a list of the most important scenarios that should be covered. You will present those scenarios to the team. Please, think about those scenarios and provide a written description for 5-7 of them in a form you find fitting.  
We will appreciate it if you can share your ideas and the process of decision making.
Please, implement the 3 most important scenarios. Use Kotlin or Java as a programming language (we prefer Kotlin, but Java is also fine). Provide instructions on how to run tests locally and see a report on their execution.


# How to run tests locally
1. You need to have  <a href="https://hub.docker.com/r/jetbrains/teamcity-server/">Docker container with TeamCity-server</a> with minimum one authorized agent.
```bash
git clone https://github.com/ikhromova/teamcity-interview.git
```
3. Run tests with parameters:
```bash
./gradlew test -Ddomain=<hostname> -Dtoken=<token>
```
where 
 - hostname = hostname of TeamCity-server instance
 - token - super user authentication token (it can be found in Docker container logs)

Example:
```bash
./gradlew test -Ddomain=localhost:8111 -Dtoken=8396052050337377335
```

# How to look at the report