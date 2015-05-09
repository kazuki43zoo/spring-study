package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigurationWithXmlMetadataTest {

    @Test
    public void configure() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        context.registerShutdownHook();
        TestBean testBean = context.getBean("testBean", TestBean.class);
        testBean.print();
    }

}
