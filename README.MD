# Elasticsearch Blog Post

Simple [Spring Boot](http://projects.spring.io/spring-boot/) with Elasticsearch app.

## Requirements

For building and running the application you need:

- [JDK 11](https://openjdk.java.net/projects/jdk/11/)
- [Elasticsearch>6](https://www.elastic.co)
- [Maven](https://maven.apache.org)

## Running the application locally

First you need to adjust your Elasticsearch configurations in src/main/resources.

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.gentaliti.ElasticsearchApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```