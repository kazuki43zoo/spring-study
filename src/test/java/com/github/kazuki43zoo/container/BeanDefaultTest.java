package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDefaultTest {

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextDefault.xml");
        context.registerShutdownHook();

        System.out.println("loaded application context.");

        ParentBean bean = context.getBean("parentBean", ParentBean.class);
        System.out.println(bean.getMessage());

        CollectionsBean collectionsBean = context.getBean("collectionsBean", CollectionsBean.class);
        System.out.println(collectionsBean.getProperties());
    }

}
