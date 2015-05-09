package com.github.kazuki43zoo.container;

import org.springframework.stereotype.Component;

@Component
public class AnnotatedTestBean3Impl {

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }
}
