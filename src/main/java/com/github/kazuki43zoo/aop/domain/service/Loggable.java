package com.github.kazuki43zoo.aop.domain.service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Loggable {
}
