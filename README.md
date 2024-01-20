## Prerequisites

Before you begin, ensure you have the following installed:
- Java JDK 17
- Maven
- MySQL Server

## Setting Up the Database

1. **Create the Database**:
   Run the following MySQL commands to create the required database and table:

   ```sql
   CREATE DATABASE proshop;
   USE proshop;

   CREATE TABLE products (
       id INT AUTO_INCREMENT NOT NULL,
       name VARCHAR(255) NOT NULL,
       description TEXT,
       price DOUBLE,
       PRIMARY KEY (id)
   );



## Configure Database Credentials
Open application.properties in the project directory and modify the database credentials as per your MySQL setup:

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/proshop
spring.datasource.username=rowelUser   #add your own configured user
spring.datasource.password=ABC123      #add your own configured  password
```
## Build the Application

Navigate to the root directory of the project and run the following Maven command to build the application:

```bash
mvn clean install
```
## Run the Application
After successfully building the project, run the following command to start the application:

```bash
mvn spring-boot:run
```
## Access the Application:
Open your web browser and go to http://localhost:8082 (default port) to access the application.

## License
This project is licensed under the GNU General Public License v3.0.





