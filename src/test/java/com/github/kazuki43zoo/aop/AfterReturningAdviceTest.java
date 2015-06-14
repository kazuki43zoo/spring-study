package com.github.kazuki43zoo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AfterReturningAdviceTest.Config.class)
public class AfterReturningAdviceTest {

    @Autowired
    TestService testService;

    @Test
    public void enableUsingAnnotation() {
        testService.execute();
        testService.execute1();
        testService.execute2();
    }

    @Configuration
    @EnableAspectJAutoProxy
    static class Config {

        @Bean
        TestService testService() {
            return new TestService();
        }

        @Bean
        TraceLoggingAspect traceLoggingAspect() {
            return new TraceLoggingAspect();
        }

    }

    @Aspect
    static class TraceLoggingAspect {

        @AfterReturning(value = "target(testService)", argNames = "testService,returnValue", returning = "returnValue")
        public void advice1(JoinPoint joinPoint, TestService testService, Object returnValue) {
            System.out.println("★★★start at advice 1★★★★");
            System.out.println(returnValue);
        }

        @AfterReturning(value = "within(com.github.kazuki43zoo.aop.AfterReturningAdviceTest.TestService)", returning = "returnValue")
        public void advice2(Number returnValue) {
            System.out.println("★★★start at advice 2★★★★");
            System.out.println(returnValue);
        }
        @AfterReturning(value = "within(com.github.kazuki43zoo.aop.AfterReturningAdviceTest.TestService)", returning = "returnValue")
        public void advice3(BigDecimal returnValue) {
            System.out.println("★★★start at advice 3★★★★");
            System.out.println(returnValue);
        }

    }

    static class TestService {

        public Object execute() {
            System.out.println(getClass().getName() + "#execute called.");
            return new BigDecimal(0);
        }

        public Object execute1() {
            System.out.println(getClass().getName() + "#execute1 called.");
            return 2;
        }

        public BigDecimal execute2() {
            System.out.println(getClass().getName() + "#execute2 called.");
            return new BigDecimal(3);
        }


    }


}
