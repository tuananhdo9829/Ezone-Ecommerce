package com.tuananhdo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.tuananhdo")
@EnableJpaRepositories("com.tuananhdo.repository")
@EntityScan("com.tuananhdo.entity")
@SpringBootApplication
public class TuananhdoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuananhdoApplication.class, args);
    }

}
