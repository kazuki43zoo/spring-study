package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomizationBeanTest {

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextBeanPostProcessor.xml");
        context.registerShutdownHook();

        AnnotatedParentBean bean = context.getBean("annotatedParentBean", AnnotatedParentBean.class);
        System.out.println(bean.getMessage());
    }

}
