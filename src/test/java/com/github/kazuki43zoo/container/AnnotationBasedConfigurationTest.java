package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class AnnotationBasedConfigurationTest {

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();

        AnnotatedParentBean parentBean = context.getBean("parentBean", AnnotatedParentBean.class);
        System.out.println(parentBean.getMessage());

    }

    @Configurable
    static class Config {

        @Bean
        AnnotatedParentBean parentBean() {
            return new AnnotatedParentBean();
        }

        @Bean
        ChildBean childBean() {
            return new ChildBean();
        }


    }

}
