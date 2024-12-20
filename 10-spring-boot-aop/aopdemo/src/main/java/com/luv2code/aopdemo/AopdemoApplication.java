package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.dao.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO accountDao, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService) {
        return runner -> {
            // this.demoTheBeforeAdvice(accountDao, membershipDAO);
            // this.demoTheAfterReturningAdvice(accountDao);
            // this.demoTheAfterThrowingAdvice(accountDao);
            // this.demoTheAfterAdvice(accountDao);
            // this.demoTheAroundAdvice(trafficFortuneService);
            // this.demoTheAroundAdviceHandleException(trafficFortuneService);
            this.demoTheAroundAdviceRethrowException(trafficFortuneService);
        };
    }

    private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundAdviceHandleException");
        System.out.println("Calling getFortune()");

        boolean tripWire = true;
        String data = trafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundAdviceHandleException");
        System.out.println("Calling getFortune()");

        boolean tripWire = true;
        String data = trafficFortuneService.getFortune(tripWire);

        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundAdvice");
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune();
        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO accountDao) {
        List<Account> accountList = null;

        try {
            boolean tripWire = false;
            accountList = accountDao.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\n\nMain Program: ... caught exception: " + e);
        }

        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
        System.out.println("----");

        System.out.println(accountList);

        System.out.println("\n");
    }

    private void demoTheAfterThrowingAdvice(AccountDAO accountDao) {
        List<Account> accountList = null;

        try {
            boolean tripWire = true;
            accountList = accountDao.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\n\nMain Program: ... caught exception: " + e);
        }

        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
        System.out.println("----");

        System.out.println(accountList);

        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO accountDao) {
        List<Account> accountList = accountDao.findAccounts();

        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("----");

        System.out.println(accountList);

        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO accountDao, MembershipDAO membershipDAO) {

        // call the business method
        Account account = new Account();
        account.setName("Madhu");
        account.setLevel("Platinum");
        accountDao.addAccount(account, true);
        accountDao.doWork();

        // call the accountDao getter/setter methods
        accountDao.setName("foobar");
        accountDao.setServiceCode("silver");

        String name = accountDao.getName();
        String code = accountDao.getServiceCode();

        // call the membership business method
        membershipDAO.addSillMember();
        membershipDAO.goToSleep();

    }

}
