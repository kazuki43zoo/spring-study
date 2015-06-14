package com.github.kazuki43zoo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
@ContextConfiguration(classes = AfterAdviceTest.Config.class)
public class AfterAdviceTest {

    @Autowired
    TestService testService;

    @Test
    public void test1() {
        testService.execute();
    }

    @Test(expected = NullPointerException.class)
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

        @After(value = "target(testService)", argNames = "testService")
        public void advice1(JoinPoint joinPoint, TestService testService) {
            System.out.println("★★★start at advice 1★★★★");
        }

    }

    static class TestService {

        public Object execute() {
            System.out.println(getClass().getName() + "#execute called.");
            return new BigDecimal(0);
        }

        public Object execute1() {
            System.out.println(getClass().getName() + "#execute1 called.");
            if (true) {
                throw new NullPointerException();
            }
            return 2;
        }

    }


}
