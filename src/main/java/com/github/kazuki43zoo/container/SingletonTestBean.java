package com.github.kazuki43zoo.container;

import org.springframework.stereotype.Component;

@Component("testBean")
public class SingletonTestBean {
    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }
}
