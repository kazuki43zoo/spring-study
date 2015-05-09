package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InstantiatingTest {

    @Test
    public void configureUsingXml() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextInstantiating.xml");
        context.registerShutdownHook();

        context.getBean("testBean", TestBean.class);
        context.getBean("testBeanCreatedByStaticMethod", TestBean.class);
        context.getBean("testBeanCreatedByInstanceMethod", TestBean.class);
        context.getBean("testBeanCreatedByFactoryBean", TestBean.class);
    }


    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();

        context.getBean("testBean", TestBean.class);
        context.getBean("testBeanCreatedByStaticMethod", TestBean.class);
        context.getBean("testBeanCreatedByInstanceMethod", TestBean.class);
        context.getBean("testBeanCreatedByFactoryBean", TestBean.class);
    }

    @Configuration
    static class Config {

        @Bean
        TestBean testBean() {
            return new TestBean();
        }

        @Bean
        TestBean testBeanCreatedByStaticMethod() {
            return StaticTestBeanFactory.createTestBean();
        }

        @Bean
        InstanceTestBeanFactory instanceTestBeanFactory() {
            return new InstanceTestBeanFactory();
        }

        @Bean
        TestBean testBeanCreatedByInstanceMethod() {
            return instanceTestBeanFactory().createTestBean();
        }

        @Bean
        TestBeanFactoryBean testBeanCreatedByFactoryBean() {
            return new TestBeanFactoryBean();
        }


    }
}
