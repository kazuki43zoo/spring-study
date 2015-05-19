package io.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ComponentScanTest {

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        context.registerShutdownHook();

        System.out.println(context.containsBean("sampleBean"));
        System.out.println(context.containsBean("configurationBean"));

    }

    @Configuration
    @ComponentScan("io.github.kazuki43zoo")
    static class AppConfig {

    }

}
