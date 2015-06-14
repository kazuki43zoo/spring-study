package com.github.kazuki43zoo.aop.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AopSampleService {

    public String hello(String name) {
        System.out.println(getClass().getName() + "#hello called.");
        return "hello " + name + " !";
    }

}
