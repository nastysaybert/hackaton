package ru.tele2.autoct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan({"ru.tele2.autoct.jpa.entity"})
public class AutoCTApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(AutoCTApplication.class, args);
    }
}
