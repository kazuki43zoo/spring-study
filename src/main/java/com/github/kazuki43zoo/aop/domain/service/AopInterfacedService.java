package com.github.kazuki43zoo.aop.domain.service;

public class AopInterfacedService implements AopService {

    public String hello(String name) {
        System.out.println(getClass().getName() + "#hello called.");
        return "hello " + name + " !";
    }

}
