package com.github.kazuki43zoo.aop.domain.service;

import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class AopSubTypedService extends AopSampleService {

    public String hello(String name) {
        System.out.println(getClass().getName() + "#hello called.");
        return "hello " + name + " !";
    }

    public String goodBy() {
        System.out.println(getClass().getName() + "#goodBy called.");
        return "goodBy !!";
    }

    public void setLogger(Logger logger) {
        System.out.println(getClass().getName() + "#setLogger called.");
    }

    @Transactional
    public void execute(InputBean inputBean) {
        System.out.println(getClass().getName() + "#execute called.");
    }

}
