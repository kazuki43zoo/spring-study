package com.github.kazuki43zoo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AfterThrowingAdviceTest.Config.class)
public class AfterThrowingAdviceTest {

    @Autowired
    TestService testService;

    @Test(expected = OptimisticLockingFailureException.class)
    public void test1() {
        testService.execute();
    }

    @Test(expected = DuplicateKeyException.class)
    public void test2() {
        testService.execute1();
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

        @AfterThrowing(value = "target(testService)", argNames = "testService,e", throwing = "e")
        public void advice1(JoinPoint joinPoint, TestService testService, DataAccessException e) {
            System.out.println("★★★start at advice 1★★★★");
            System.out.println(e);
        }

        @AfterThrowing(value = "within(com.github.kazuki43zoo.aop.AfterThrowingAdviceTest.TestService)", throwing = "e")
        public void advice2(OptimisticLockingFailureException e) {
            System.out.println("★★★start at advice 2★★★★");
            System.out.println(e);
        }


    }

    static class TestService {

        public Object execute() {
            if (true) {
                throw new OptimisticLockingFailureException("lock error.");
            }
            return "return value of execute";
        }

        public Object execute1() {
            if (true) {
                throw new DuplicateKeyException("lock error 1.");
            }
            return "return value of execute";
        }

    }


}
