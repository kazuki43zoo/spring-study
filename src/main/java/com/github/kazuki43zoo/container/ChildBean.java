package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.BeanNameAware;

public class ChildBean implements BeanNameAware {
    private String beanName;

    public ChildBean() {
        System.out.println(getClass());
    }

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }

    public void init() {
        System.out.println("init:" + beanName);
    }

    public void destroy() {
        System.out.println("destroy:" + beanName);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
