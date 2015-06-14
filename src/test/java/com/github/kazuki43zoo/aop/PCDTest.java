package com.github.kazuki43zoo.aop;

import com.github.kazuki43zoo.aop.domain.service.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PCDTest.Config.class)
public class PCDTest {

    @Autowired
    AopSampleService aopSampleService;
    @Autowired
    AopService aopInterfacedService;
    @Autowired
    AopSubTypedService aopSubTypedService;

    @Test
    public void enableUsingAnnotation() {

        System.out.println(aopSampleService.hello("Kazuki"));
        System.out.println(aopInterfacedService.hello("Shimizu"));
        System.out.println(aopSubTypedService.hello("Kazuki Shimizu"));
        System.out.println(aopSubTypedService.goodBy());
        aopSubTypedService.setLogger(LoggerFactory.getLogger(PCDTest.class));
        aopSubTypedService.execute(new InputBean());

    }

    @Configuration
    @EnableAspectJAutoProxy
    static class Config {

        @Bean
        TraceLoggingAspect traceLogAspect() {
            return new TraceLoggingAspect();
        }

        @Bean
        AopSampleService aopSampleService() {
            return new AopSampleService();
        }

        @Bean
        AopService aopInterfacedService() {
            return new AopInterfacedService();
        }

        @Bean
        AopSubTypedService aopSubTypedService() {
            return new AopSubTypedService();
        }

    }

    @Aspect
    static class TraceLoggingAspect {

        @Before("execution(* com.github.kazuki43zoo.aop.domain.service.*.*(..))")
        public void loggingBeginByExecution(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by execution★★★★");
        }

        @Before("within(com.github.kazuki43zoo.aop.domain.service.AopSampleService))")
        public void loggingBeginByWithin(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by within★★★★");
        }

        @Before("this(com.github.kazuki43zoo.aop.domain.service.AopSampleService))")
        public void loggingBeginByThis(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by this★★★★");
        }

        @Before("target(com.github.kazuki43zoo.aop.domain.service.AopInterfacedService))")
        public void loggingBeginByTarget(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by target★★★★");
        }

        @Before("args(java.lang.String))")
        public void loggingBeginByArgs(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by args with String★★★★");
        }

        @Before("args(java.lang.Object))")
        public void loggingBeginByArgsBaseClass(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by args with Object★★★★");
        }

        @Before("args(org.slf4j.Logger))")
        public void loggingBeginByArgsWithInterface(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by args with interface★★★★");
        }

        @Before("@args(com.github.kazuki43zoo.aop.domain.service.Loggable))")
        public void loggingBeginByAtArgs(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by @args★★★★");
        }
        @Before("@target(org.springframework.transaction.annotation.Transactional))")
        public void loggingBeginByAtTarget(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by @target★★★★");
        }

        @Before("@within(org.springframework.transaction.annotation.Transactional))")
        public void loggingBeginByAtWithin(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by @within★★★★");
        }

        @Before("@annotation(org.springframework.transaction.annotation.Transactional))")
        public void loggingBeginByAtAnnotation(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by @annotation★★★★");
        }


        @Before("bean(aopSampleService))")
        public void loggingBeginByBean(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by bean★★★★");
        }

        @Before("bean(*Service))")
        public void loggingBeginByBeanWithWildcard(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start by bean with wildcard★★★★");
        }

    }


}
