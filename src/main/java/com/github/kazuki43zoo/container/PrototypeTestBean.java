package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("prototypeTestBean")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PrototypeTestBean {
    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }
}
