package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
        return this.findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("No soup for you!!!");
        }

        List<Account> accounts = new ArrayList<>();

        Account account1 = new Account("John", "Silver");
        Account account2 = new Account("Madhu", "Platinum");
        Account account3 = new Account("Luca", "Gold");

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        return accounts;
    }

    @Override
    public void addAccount(Account account, boolean cipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": int getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": int setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": int getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": int setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
