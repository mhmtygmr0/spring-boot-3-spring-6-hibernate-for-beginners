package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
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
    public CommandLineRunner commandLineRunner(AccountDAO accountDao, MembershipDAO membershipDAO) {
        return runner -> {
            this.demoTheBeforeAdvice(accountDao, membershipDAO);
        };
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
