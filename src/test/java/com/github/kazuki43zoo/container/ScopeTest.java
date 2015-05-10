package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ScopeTest {

    @Test
    public void configureUsingXml() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextScope.xml");
        context.registerShutdownHook();

        Object singletonBean = context.getBean("testBean");
        assertThat(singletonBean, is(context.getBean("testBean")));

        Object prototypeBean = context.getBean("prototypeTestBean");
        assertThat(prototypeBean, not(context.getBean("prototypeTestBean")));

    }

    @Test
    public void configureJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();

        Object singletonBean = context.getBean("testBean");
        assertThat(singletonBean, is(context.getBean("testBean")));

        Object prototypeBean = context.getBean("prototypeTestBean");
        assertThat(prototypeBean, not(context.getBean("prototypeTestBean")));
    }

    @Test
    public void configureAnnotationConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigWithComponentScan.class);
        context.registerShutdownHook();

        Object singletonBean = context.getBean("testBean");
        assertThat(singletonBean, is(context.getBean("testBean")));

        Object prototypeBean = context.getBean("prototypeTestBean");
        assertThat(prototypeBean, not(context.getBean("prototypeTestBean")));
    }

    @Configuration
    static class Config {

        @Bean
        TestBean testBean() {
            return new TestBean();
        }

        @Bean
        @Scope(BeanDefinition.SCOPE_PROTOTYPE)
        TestBean prototypeTestBean() {
            return new TestBean();
        }

    }

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(Configuration.class))
    static class ConfigWithComponentScan {

        @Bean
        ChildBean childBean() {
            return new ChildBean();
        }

    }

}
