package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedTestBean {
    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }
}
