package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class ConfigurationWithAnnotationMetadataTest {

    @Test
    public void configure() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();
        TestBean testBean = context.getBean("testBean", TestBean.class);
        testBean.print();
    }

    @Configuration
    @ComponentScan
    static class Config {
    }

}
