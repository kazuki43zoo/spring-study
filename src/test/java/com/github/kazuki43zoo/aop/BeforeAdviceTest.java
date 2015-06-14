package com.github.kazuki43zoo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeforeAdviceTest.Config.class)
public class BeforeAdviceTest {

    @Autowired
    TestService testService;

    @Test
    public void enableUsingAnnotation() {
        testService.execute();
        testService.execute1("test");
        testService.execute2(100, "test2");
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

        @Before(value = "target(testService)" ,argNames = "testService")
        public void advice1(JoinPoint joinPoint,TestService testService) {
            // ...
            System.out.println("★★★start at advice 1★★★★");
            System.out.println(testService);
        }

        @Before(value="within(com.github.kazuki43zoo.aop.BeforeAdviceTest.TestService) && (args(message) || args(*,message))", argNames = "message")
        public void advice2(String message) {
            // ...
            System.out.println("★★★start at advice 2★★★★");
            System.out.println(message);
        }

    }

    static class TestService {

        public void execute() {
            System.out.println(getClass().getName() + "#execute called.");
        }

        public void execute1(String message) {
            System.out.println(getClass().getName() + "#execute1 called.");
        }

        public void execute2(int charge,String message) {
            System.out.println(getClass().getName() + "#execute2 called.");
        }

    }


}
