package ru.job4j.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Старт REST сервиса Банк
 */
@SpringBootApplication
public class Job4jBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(Job4jBankApplication.class, args);
        System.out.println("Start service /bank /user /account on http://localhost:8080/");
    }

}