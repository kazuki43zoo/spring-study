package com.github.kazuki43zoo.container;

import org.springframework.stereotype.Component;

public class ChildBean {
    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }
}
