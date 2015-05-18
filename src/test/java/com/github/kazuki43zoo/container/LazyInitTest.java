package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LazyInitTest {

    @Test
    public void configureUsingXml() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextLazyInit.xml");
        context.registerShutdownHook();

        System.out.println("before getBean(lazyInitBean)");
        TestBean lazyInitBean = context.getBean("lazyInitBean", TestBean.class);
        assertThat(lazyInitBean.getMessage(), is("Hello World by TestBean"));


    }

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();

        System.out.println("before getBean(lazyInitBean)");
        TestBean lazyInitBean = context.getBean("lazyInitBean", TestBean.class);
        assertThat(lazyInitBean.getMessage(), is("Hello World by TestBean"));

    }


    @Test
    public void configureUsingAnnotationConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        context.registerShutdownHook();

        System.out.println("before getBean(lazyInitBean)");
        AnnotatedLazyBean lazyInitBean = context.getBean("lazyInitBean", AnnotatedLazyBean.class);
        assertThat(lazyInitBean.getMessage(), is("Hello World by AnnotatedLazyBean"));

    }


    @Configuration
    static class Config {

        @Bean
        @Lazy
        TestBean lazyInitBean() {
            return new TestBean();
        }

    }


    @Configuration
    @ComponentScan(useDefaultFilters = false, includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = AnnotatedLazyBean.class))
    static class ComponentScanConfig {
    }

}
