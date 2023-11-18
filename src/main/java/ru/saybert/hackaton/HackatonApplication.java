package ru.saybert.hackaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan({"ru.saybert.hackaton.jpa.entity"})
public class HackatonApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(HackatonApplication.class, args);
    }
}
