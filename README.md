# Dropshipping

Dropshipping is a Spring Boot Applciation.

## Requirements

For building and running the application you need:
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [PostgreSQL](https://www.postgresql.org/)

## Create Database
```
sudo -u postgres psql

CREATE DATABASE testdb;
CREATE USER testuser WITH PASSWORD '12345678';
GRANT ALL PRIVILEGES ON DATABASE testdb TO testuser;
```

## Application.yml
To create the tables in the database, first set ddl-auto=create and run the application.
Then if you want you can set ddl-auto=none and continue working.
You can use profiles for database schema and data .
```
spring:
  datasource:

#    url: jdbc:postgresql://X.X.X.X:5432/proddb
#    username: produser
#    password: 12345678

    url: jdbc:postgresql://localhost:5432/testdb
    username: testuser
    password: 12345678

  jpa:
    hibernate:
#      ddl-auto: create
      ddl-auto: none


  profiles:
#    include: dropshipping,flyway
    include: dropshipping
#    include: dropshipping,generate-ddl




```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.mustceng.dropshipping.DropshippingApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
```shell
mvn spring-boot:run
```


## JUnit and Integration Test
Execute the `main` method in the `com.mustceng.dropshipping.DropshippingApplicationTests` class from your IDE.


## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[Mustafa Çoban](https://github.com/mustceng)
