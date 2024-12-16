package com.luv2code.aopdemo.dao.service;

import java.util.concurrent.TimeUnit;

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
}
