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
        TestBean testBean = context.getBean(TestBean.class);
        testBean.print();
        context.close();
    }

    @Configuration
    @ComponentScan
    static class Config {
    }

}
