package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConfigurationWithXmlMetadataTest {

    @Test
    public void configure() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        TestBean testBean = context.getBean(TestBean.class);
        testBean.print();
        context.close();
    }

}
