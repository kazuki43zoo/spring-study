package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomizationMetadataTest {

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextBeanFactoryPostProcessor.xml");
        context.registerShutdownHook();
    }

}
