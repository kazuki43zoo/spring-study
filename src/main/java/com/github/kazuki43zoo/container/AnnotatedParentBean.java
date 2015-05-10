package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("fieldParentBean")
public class AnnotatedParentBean {

    @Autowired
    private ChildBean childBean;

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName() + " with " + childBean.getClass().getSimpleName();
    }

}
