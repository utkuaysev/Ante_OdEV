package com.project.corona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EntityScan("com.project.corona.entity")
@ComponentScan(basePackages = {"com.project.corona.*"})
@EnableMongoRepositories("com.project.corona.repository")
@SpringBootApplication
public class CoronaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoronaApplication.class, args);
    }

}
