package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDao) {
        return runner -> {
            System.out.println("Hello World!");

            this.demoTheBeforeAdvice(accountDao);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO accountDao) {

        // call the business method
        accountDao.addAccount();

        // do it again!
        System.out.println("\n let's call it again!");

        // call the business method again
        accountDao.addAccount();
    }

}
