package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Transactional
@Component("fieldParentBean")
public class AnnotatedParentBean implements InitializingBean{

    @Autowired
    private ChildBean childBean;

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName() + " with " + childBean.getClass().getSimpleName();
    }

    public void initMethod(){
        System.out.println("initMethod.");
    }

    public void destroyMethod(){
        System.out.println("destroyMethod.");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("postConstruct.");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("postConstruct.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet.");
    }
}
