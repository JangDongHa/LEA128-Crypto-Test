package com.example.cryptospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CryptospringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptospringApplication.class, args);
    }

}
