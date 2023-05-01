### How-to-use

_Below the steps to follow in order to use the APIs_

1. Clone the repo
   ```sh
   git clone https://github.com/swirice/florence-consulting-exercise.git
   ```
2. Install Docker
   > https://www.docker.com/products/docker-desktop/
3. Move inside the project folder
4. Customize the environment variables in the .env file
5. Run this command to compile the project and build the .jar
   ```sh
   mvn clean install -DskipTests
   ```
6. Run this command to start the microservice with database embedded as Docker container
   ```sh
   docker-compose up -d
   ```