package com.github.kazuki43zoo.aop;

import com.github.kazuki43zoo.aop.domain.service.AopSampleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class AspectJStyleTest {


    @Test
    public void enableUsingAnnotation() {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();

        AopSampleService service = context.getBean("aopSampleService", AopSampleService.class);

        System.out.println(service.hello("Kazuki"));
        ;

    }

    @Configuration
    @EnableAspectJAutoProxy
    static class Config {

        @Bean
        TraceLogAspect traceLogAspect() {
            return new TraceLogAspect();
        }

        @Bean
        AopSampleService aopSampleService() {
            return new AopSampleService();
        }

    }

    @Aspect
    static class TraceLogAspect {

        @Before("execution(* com.github.kazuki43zoo.aop.domain.service.*.*(..))")
        public void loggingBegin(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start★★★★");
        }

        @After("execution(* com.github.kazuki43zoo.aop.domain.service.*.*(..))")
        public void loggingEnd(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★end★★★★");
        }

        @Pointcut("execution(* com.github.kazuki43zoo.aop.domain.service.*.*(..))")
        private void anyServiceOperation() {
        }

        @Before("anyServiceOperation()")
        public void loggingBeginUsingPointcut(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start using @Pointcut★★★★");
        }

        @After("anyServiceOperation()")
        public void loggingEndUsingPointcut(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★end using @Pointcut★★★★");
        }

    }


}
