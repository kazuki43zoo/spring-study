package com.github.kazuki43zoo.container;

import org.springframework.stereotype.Component;

@Component("testBean2")
public class AnnotatedTestBean2 {

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }
}
