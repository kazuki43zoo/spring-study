package com.github.kazuki43zoo.aop;

import com.github.kazuki43zoo.aop.domain.service.AopSampleService;
import org.aspectj.lang.JoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class XmlSchemaStyleTest {

    @Autowired
    AopSampleService service;

    @Test
    public void hello() {

        System.out.println(service.hello("Kazuki"));

    }

    public static class TraceLoggerAspect {

        public void loggingBegin(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★start★★★★");
        }

        public void loggingEnd(JoinPoint joinPoint) {
            // ...
            System.out.println("★★★end★★★★");
        }

    }


}
