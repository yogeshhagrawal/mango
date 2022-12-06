# mango

This project is a test framework built to test search and filter functionality on amazon web app.
Currently, it has the test scenario given in the problem statement but can be extended to include more scenarios in future.
The test class is `SearchTest`.
Tests can be run on multiple browser which can be passed through config files, for e.g. FIREFOX, EDGE. Default browser is set to CHROME.

# Dependencies

- Java 8
- Maven version 3.8.6 or above

# Run the project
- Fork this repo 
- Make sure to be in the root directory where your pom.xml resides
- Clean and build the project using command:
```
mvn clean install
```
The above command will fetch all the dependencies and build the project.
This will also run all the tests in the project.

- Alternatively, you can also run just the test after successfully building the project once by running above command.
```
mvn test
```
- You can also pass the environment variable which is used to read the properties file containing the base api url. Can specify browser in properties file
```
mvn clean install -Denv=qa
```

