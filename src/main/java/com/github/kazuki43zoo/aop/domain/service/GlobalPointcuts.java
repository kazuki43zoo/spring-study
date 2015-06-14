package com.github.kazuki43zoo.aop.domain.service;

import org.aspectj.lang.annotation.Pointcut;

public class GlobalPointcuts {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void anyService() {
    }

    @Pointcut("@within(org.springframework.stereotype.Repository)")
    public void anyRepository() {
    }

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void anyController() {
    }

}
