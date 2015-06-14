package com.github.kazuki43zoo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
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
@ContextConfiguration(classes = IntroductionTest.Config.class)
public class IntroductionTest {

    @Autowired
    TestService testService;
    @Autowired
    UsageTracked usageTracked;

    @Test
    public void test1() {
        testService.execute();
        System.out.println(usageTracked.getCount());
    }


    @Configuration
    @EnableAspectJAutoProxy
    static class Config {

        @Bean
        TestService testService() {
            return new TestService();
        }

        @Bean
        UsageTracking traceLoggingAspect() {
            return new UsageTracking();
        }

    }

    @Aspect
    static class UsageTracking {

        @DeclareParents(value="com.github.kazuki43zoo.aop.IntroductionTest.*", defaultImpl=DefaultUsageTracked.class)
        public static UsageTracked mixin;

        @Before(value = "this(usageTracked)", argNames = "usageTracked")
        public void advice1(JoinPoint joinPoint, UsageTracked usageTracked) {
            System.out.println("★★★start advice★★★★");
            usageTracked.incrementUseCount();
        }

    }

    static class TestService {

        public Object execute() {
            System.out.println(getClass().getName() + "#execute called.");
            return new BigDecimal(0);
        }

    }

    static interface UsageTracked {
        void incrementUseCount();

        int getCount();
    }

    public static class DefaultUsageTracked implements UsageTracked {
        private int counter;

        @Override
        public void incrementUseCount() {
            counter++;
        }

        @Override
        public int getCount() {
            return counter;
        }
    }

}
