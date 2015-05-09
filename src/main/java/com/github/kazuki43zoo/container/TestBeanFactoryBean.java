package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.FactoryBean;

public class TestBeanFactoryBean implements FactoryBean<TestBean> {

    public TestBean getObject() throws Exception {
        return new TestBean();
    }

    public Class<?> getObjectType() {
        return TestBean.class;
    }

    public boolean isSingleton() {
        return true;
    }

}
