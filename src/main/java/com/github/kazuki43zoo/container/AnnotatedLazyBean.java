package com.github.kazuki43zoo.container;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("lazyInitBean")
@Lazy
public class AnnotatedLazyBean {
    public AnnotatedLazyBean() {
        System.out.println(getClass());
    }

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }
}
