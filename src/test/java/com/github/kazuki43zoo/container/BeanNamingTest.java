package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BeanNamingTest {

    @Test
    public void configureUsingXml() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextNaming.xml");
        context.registerShutdownHook();
        Object bean = context.getBean("testBean2");
        assertThat(context.getBean("testBean2Alias1"), is(bean));
        assertThat(context.getBean("testBean2Alias2"), is(bean));
        assertThat(context.getBean("testBean2Alias3"), is(bean));
        assertThat(context.getBean("testBean2Alias4"), is(bean));
        assertThat(context.getBean("testBean2Alias5"), is(bean));
        context.getBean("testBean3");
        context.getBean("com.github.kazuki43zoo.container.TestBean3#0");
        assertThat(context.getBeanDefinitionCount(), is(3));
    }

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(BeanNameConfig.class);
        context.registerShutdownHook();
        Object bean = context.getBean("testBean2");
        assertThat(context.getBean("testBean2Alias1"), is(bean));
        assertThat(context.getBeanDefinitionCount(), is(8));
    }

    @Test
    public void configureUsingAnnotation() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        context.registerShutdownHook();
        assertThat(context.containsBean("annotatedTestBean"), is(true));
        assertThat(context.containsBean("testBean2"), is(true));
    }

    @Test
    public void configureUsingAnnotationWithBeanNameGenerator() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(ComponentScanConfigWithBeanNameGenerator.class);
        context.registerShutdownHook();
        assertThat(context.containsBean("annotatedTestBean3"), is(true));
    }


    @Configuration
    static class BeanNameConfig {

        @Bean(name = {"testBean2", "testBean2Alias1"})
        TestBean2 testBean2() {
            return new TestBean2();
        }

    }

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(Configuration.class))
    static class ComponentScanConfig {
    }

    @Configuration
    @ComponentScan(
            excludeFilters = @ComponentScan.Filter(Configuration.class),
            nameGenerator = TrimmingImplAnnotationBeanNameGenerator.class
    )
    static class ComponentScanConfigWithBeanNameGenerator {
    }

}
