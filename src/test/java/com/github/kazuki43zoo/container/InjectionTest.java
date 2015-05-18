package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class InjectionTest {

    @Test
    public void configureUsingXml() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextInjection.xml");
        context.registerShutdownHook();
        ParentBean parentBean;

        parentBean = context.getBean("constructorParentBean", ParentBean.class);
        assertThat(parentBean.getMessage(), is("Hello World by ParentBean with ChildBean"));

        parentBean = context.getBean("setterParentBean", ParentBean.class);
        assertThat(parentBean.getMessage(), is("Hello World by ParentBean with ChildBean"));

        AnnotatedParentBean annotatedParentBean = context.getBean("fieldParentBean", AnnotatedParentBean.class);
        assertThat(annotatedParentBean.getMessage(), is("Hello World by AnnotatedParentBean with ChildBean"));

        ParentMultiBean parentMultiBean = context.getBean("parentMultiBean", ParentMultiBean.class);
        assertThat(parentMultiBean.getMessage(), is("Hello World by ParentMultiBean with ChildBean and ChildBean2"));

        ConfigBean configBean = context.getBean("configBean", ConfigBean.class);
        assertThat(configBean.getString(), is("String Value"));
        assertThat(configBean.getInteger(), is(2015));
        Properties expectedProperties = new Properties() {
            {
                setProperty("prop1", "value1");
                setProperty("prop2", "value2");
            }
        };
        assertThat(configBean.getProperties(), is(expectedProperties));

        configBean = context.getBean("idRefConfigBean", ConfigBean.class);
        assertThat(configBean.getString(), is("childBean"));

        parentBean = context.getBean("innerParentBean", ParentBean.class);
        assertThat(parentBean.getMessage(), is("Hello World by ParentBean with ChildBean"));

        CollectionsBean collectionsBean = context.getBean("extendCollectionsBean", CollectionsBean.class);
        expectedProperties = new Properties() {
            {
                setProperty("prop1", "Parent Value1");
                setProperty("prop2", "Child Value2");
                setProperty("prop3", "Child Value3");
            }
        };
        assertThat(collectionsBean.getProperties(), is(expectedProperties));


        Hierarchy1Bean hierarchy1Bean = context.getBean("hierarchy1Bean", Hierarchy1Bean.class);
        assertThat(hierarchy1Bean.getHierarchy2Bean().getHierarchy3Bean().getString(), is("Nested Bean Value"));

        System.out.println(context.getBeanDefinitionCount());
        System.out.println(Arrays.asList(context.getBeanDefinitionNames()));
    }

    @Test
    public void configureUsingHierarchyXml() {
        ConfigurableApplicationContext parentContext =
                new ClassPathXmlApplicationContext("spring/applicationContextInjectionParent.xml");
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"spring/applicationContextInjectionChildren.xml"}, parentContext);
        context.registerShutdownHook();
        ParentBean parentBean = context.getBean("parentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), is(parentContext.getBean("childBean")));
        assertThat(parentBean.getChildBean(), not(context.getBean("childBean")));
        System.out.println(ChildBean.class.cast(context.getBean("childBean")).getMessage());

    }

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();
        ParentBean parentBean;

        parentBean = context.getBean("constructorParentBean", ParentBean.class);
        assertThat(parentBean.getMessage(), is("Hello World by ParentBean with ChildBean"));

        parentBean = context.getBean("setterParentBean", ParentBean.class);
        assertThat(parentBean.getMessage(), is("Hello World by ParentBean with ChildBean"));

        AnnotatedParentBean annotatedParentBean = context.getBean("annotatedParentBean", AnnotatedParentBean.class);
        assertThat(annotatedParentBean.getMessage(), is("Hello World by AnnotatedParentBean with ChildBean"));

    }

    @Configuration
    static class Config {

        @Bean
        ChildBean childBean() {
            return new ChildBean();
        }

        @Bean
        ParentBean constructorParentBean() {
            return new ParentBean(childBean());
        }

        @Bean
        ParentBean setterParentBean() {
            ParentBean parentBean = new ParentBean();
            parentBean.setChildBean(childBean());
            return parentBean;
        }

        @Bean
        AnnotatedParentBean annotatedParentBean() {
            return new AnnotatedParentBean();
        }

        @Bean
        @Lazy
        ParentBean lazyInitBean() {
            return new ParentBean(childBean());
        }

    }

}
