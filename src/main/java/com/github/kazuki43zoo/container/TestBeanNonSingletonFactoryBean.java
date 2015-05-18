package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.FactoryBean;

public class TestBeanNonSingletonFactoryBean implements FactoryBean<TestBean> {

    public TestBean getObject() {
        return new TestBean();
    }

    public Class<?> getObjectType() {
        return TestBean.class;
    }

    public boolean isSingleton() {
        return false;
    }

}
