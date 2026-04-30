package com.example.fundoonote;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundoonoteApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
        System.setProperty("JWT_EXPIRATION", dotenv.get("JWT_EXPIRATION"));

        SpringApplication.run(FundoonoteApplication.class, args);
    }
}