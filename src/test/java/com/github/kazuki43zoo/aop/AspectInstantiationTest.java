package com.github.kazuki43zoo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AspectInstantiationTest.Config.class)
public class AspectInstantiationTest {

    @Autowired
    TestService testService;
    @Autowired
    TestService2 testService2;

    @Test
    public void test1() {
        testService.execute();
        testService2.execute();
        testService.execute1();
        testService2.execute1();
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
        TestService2 testService2() {
            return new TestService2();
        }

        @Bean
        @Scope(value = "prototype")
        MyAspect myAspect() {
            return new MyAspect();
        }

    }

    @Aspect("pertarget(within(com.github.kazuki43zoo.aop.AspectInstantiationTest.*))")
    static class MyAspect {

        private int counter;

        @Before(value = "within(com.github.kazuki43zoo.aop.AspectInstantiationTest.*)")
        public void advice1() {
            System.out.println("★★★start advice★★★★" + this);
            counter++;
            System.out.println(counter);
        }

    }

    static class TestService {

        public TestService() {
            System.out.println(getClass().getName() + " called.");
        }

        public Object execute() {
            System.out.println(getClass().getName() + "#execute called.");
            return new BigDecimal(0);
        }

        public Object execute1() {
            System.out.println(getClass().getName() + "#execute1 called.");
            return new BigDecimal(0);
        }

        public Object execute2() {
            System.out.println(getClass().getName() + "#execute2 called.");
            return new BigDecimal(0);
        }

    }

    static class TestService2 {

        public Object execute() {
            System.out.println(getClass().getName() + "#execute called.");
            return new BigDecimal(0);
        }

        public Object execute1() {
            System.out.println(getClass().getName() + "#execute1 called.");
            return new BigDecimal(0);
        }

    }

}
