### Prerequisites

_Make sure to have installed the following tools in your local environment_

* git
> https://gitforwindows.org/
* docker
> https://www.docker.com/products/docker-desktop/
* insomnia
> https://insomnia.rest/

### How-to-use

_Below the steps to follow in order to use the APIs_

1. Clone the repository
   ```sh
   git clone https://github.com/swirice/florence-consulting-exercise.git
   ```
2. Move inside the project folder
3. Customize the environment variables in the _.env_ file
4. Run this command to compile the project and build the .jar file
   ```sh
   mvn clean install -DskipTests
   ```
5. Run this command to start the microservice with database embedded as Docker container
   ```sh
   docker-compose up -d
   ```
6. Import the file _Insomnia_APIs.json_ into Insomnia and enjoy test the APIs!
