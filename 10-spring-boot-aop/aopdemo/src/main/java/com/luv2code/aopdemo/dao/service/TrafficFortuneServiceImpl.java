package com.luv2code.aopdemo.dao.service;

import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Except heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean tripWire) {

        if (tripWire) {
            throw new RuntimeException("Major accident! Highway is closed!");
        }

        return getFortune();
    }
}
