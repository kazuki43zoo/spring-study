package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.BeanNameAware;

public class LifecycleTestBean implements BeanNameAware {
    private String beanName;

    public void doInit() {
        System.out.println("doInit:" + beanName);
    }

    public void doDestroy() {
        System.out.println("doDestroy:" + beanName);
    }

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
