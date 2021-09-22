# Jetbrains-TeamCity-test-ptoject

# Task
Let’s assume you just joined the TeamCity team. Your goal is to introduce automated testing to improve the quality of the product and the development process. At first, you will need to come up with a list of the most important scenarios that should be covered. You will present those scenarios to the team. Please, think about those scenarios and provide a written description for 5-7 of them in a form you find fitting.  
We will appreciate it if you can share your ideas and the process of decision making.
Please, implement the 3 most important scenarios. Use Kotlin or Java as a programming language (we prefer Kotlin, but Java is also fine). Provide instructions on how to run tests locally and see a report on their execution.


# How to run tests locally
1. You need to have  <a href="https://hub.docker.com/r/jetbrains/teamcity-server/">Docker container with TeamCity-server</a> with minimum one authorized agent.
2. Clone the repository
```bash
git clone https://github.com/ikhromova/teamcity-interview.git
```
3. Run tests with parameters:
```bash
./gradlew clean test -Dtoken=<token> -Durl=<url> -Dgithub-token=<github-token>
```
where 
 - url - url for web interface of TeamCity-server instance
 - token - super user authentication token (it can be found in Docker container logs)
 - github-token - authentication token for test-github-user (can be found in Google Doc)

Example:
```bash
./gradlew clean test -Dtoken=8396052050337377335 -Durl=http://localhost:8111 -Dgithub-token=123qwe
```

# How to look at the report
When tests finished, run command:
```bash
 allure serve build/allure-results
```
