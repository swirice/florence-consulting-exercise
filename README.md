### Prerequisites

_Make sure to have installed the following tools in your local environment:_

* Git
> https://gitforwindows.org/
* Maven
> https://maven.apache.org/
* Docker
> https://www.docker.com/
* Insomnia
> https://insomnia.rest/

### How-to-use

_Below the steps to follow in order to use the APIs:_

1. Clone the repository
   ```sh
   git clone https://github.com/swirice/florence-consulting-exercise.git
   ```
2. Move inside the project folder
3. (Optional) Customize the environment variables in the _.env_ file
4. Compile the project and build the .jar file
   ```sh
   mvn clean install -DskipTests
   ```
5. Start the microservice with database embedded as Docker container
   ```sh
   docker-compose up -d
   ```
6. Import the file _Insomnia_APIs.json_ into Insomnia and enjoy the APIs!
