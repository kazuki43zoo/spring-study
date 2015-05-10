package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class AutowireTest {

    @Test
    public void configureUsingXml() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextAutowire.xml");
        context.registerShutdownHook();

        ParentBean parentBean = context.getBean("parentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), nullValue());

        parentBean = context.getBean("noAutowireNameParentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), nullValue());

        parentBean = context.getBean("autowireDefaultParentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), nullValue());

        parentBean = context.getBean("autowireByNameParentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), is(context.getBean("childBean")));

        parentBean = context.getBean("autowireByTypeParentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), is(context.getBean("childBean")));

        parentBean = context.getBean("autowireByTypeParentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), is(context.getBean("childBean")));

        parentBean = context.getBean("autowireConstructorParentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), is(context.getBean("childBean")));

        parentBean = context.getBean("overrideAutowireParentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), not(context.getBean("childBean")));

        CollectionsBean2 collectionsBean2 = context.getBean("collectionsBean2", CollectionsBean2.class);
        assertThat(collectionsBean2.getList().size(), is(2));
        assertThat(collectionsBean2.getSet().size(), is(2));
        assertThat(collectionsBean2.getArray().length, is(2));
        assertThat(collectionsBean2.getMap().size(), is(2));

        CollectionsBean collectionsBean = context.getBean("collectionsBean", CollectionsBean.class);
        assertThat(collectionsBean.getList().size(), is(1));
        assertThat(collectionsBean.getMap().size(), is(1));
        assertThat(collectionsBean.getSet().size(), is(2));
        assertThat(collectionsBean.getProperties().size(), is(2));


    }

    @Test
    public void configureJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();

        ParentBean parentBean = context.getBean("parentBean", ParentBean.class);
        assertThat(parentBean.getChildBean(), is(context.getBean("childBean")));

        StoreClient storeClient = context.getBean("storeClient", StoreClient.class);
        assertThat(storeClient.getStore1().get(), is("test"));
        assertThat(storeClient.getStore2().get(), is(999));

    }

    @Test
    public void configureUsingAnnotation() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextAnnotationAutowire.xml");
        context.registerShutdownHook();

        AnnotatedCollectionsBean annotatedCollectionsBean = context.getBean("annotatedCollectionsBean", AnnotatedCollectionsBean.class);
        assertThat(annotatedCollectionsBean.getList().size(), is(1));
        assertThat(annotatedCollectionsBean.getMap().size(), is(1));
        assertThat(annotatedCollectionsBean.getSet().size(), is(2));
        assertThat(annotatedCollectionsBean.getProperties().size(), is(2));
    }

    @Configuration
    static class Config {

        @Bean
        @Primary
        ChildBean childBean() {
            return new ChildBean();
        }

        @Bean
        ChildBean childBean2() {
            return new ChildBean();
        }

        @Bean(autowire = Autowire.BY_TYPE)
        ParentBean parentBean() {
            return new ParentBean();
        }

        @Bean
        StringStore stringStore() {
            return new StringStore();
        }

        @Bean
        IntegerStore integerStore() {
            return new IntegerStore();
        }

        @Bean
        StoreClient storeClient() {
            return new StoreClient();
        }

    }

    static class StoreClient {
        @Autowired
        private Store<String> store1;
        @Autowired
        private Store<Integer> store2;

        public Store<String> getStore1() {
            return store1;
        }

        public Store<Integer> getStore2() {
            return store2;
        }
    }
//
//    @Configuration
//    @ComponentScan(excludeFilters = @ComponentScan.Filter(Configuration.class))
//    static class ConfigWithComponentScan {
//
//        @Bean
//        ChildBean childBean() {
//            return new ChildBean();
//        }
//
//    }

}
