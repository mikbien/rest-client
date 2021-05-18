# rest-quotes

Application to retrieve given number of quotes from the https://kanye.rest/ api and then to analyze them via the https://sentim-api.herokuapp.com/ api.

### Technologies
- Java 8
- Spring Boot
- Thymeleaf

### Build
- clone the repository with `git clone https://github.com/mikbien/rest-client.git`
- go inside the `rest-client` directory and run the gradle wrapper to build the artifact with : `./gradlew build`
- after succesful build you will find the `.jar` file inside `<YOUR_PATH>/rest-client/build/libs` folder 

### Run/Deploy
- to run the app you will need to execute the `.jar` file inside `<YOUR_PATH>/rest-client/build/libs` folder usign `java -jar <JAR_NAME>`
- by default the app will run on `localhost:8080`, it will be accessible from there

### How to use
- go to `localhost:8080` (or whichever port you have set in your `application.properties` file)
- you will be greeted with a simple html form, in which you should insert a value from 5 to 20 (it is customizable though)
- after sending the requested value, the program will fetch all the necessary data and present it to you

### Configuration
inside the `rest-client/src/main/java/assignment/restclient/services/ServiceConfig.java` file you can configure the following properties:
- `quotes` - set it to the url of the quote generator api
- `analyze` - set it to the url of the semantics analyzer api
- `lowerBound` - set it to a desired lowerbound of the number of requested quotes
- `upperBound` set it to a desired upperbound of the number of requested
inside the `rest-client/src/main/resources/application.properties` file you can configure the following properties:
- `server.port` - configure the port on which the application is running

### Features
Some interesting features to note:
- concurrent get/post requests - because of the simplicity of provided apis, the response time after increasing the number of quotes from the values specified in the assignment to values > 100, this program was having a hard time delievering data on time - to resolve this issue, I decided to implement the use of WebClient's parallel functionality - this had a huge positive impact on the performance of the whole application.
- thymeleaf html generation
