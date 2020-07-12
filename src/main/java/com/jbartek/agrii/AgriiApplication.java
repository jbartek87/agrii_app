package com.jbartek.agrii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AgriiApplication{

    public static void main(String[] args) {
        SpringApplication.run(AgriiApplication.class, args);
    }

}
