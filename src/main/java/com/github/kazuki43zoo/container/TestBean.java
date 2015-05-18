package com.github.kazuki43zoo.container;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;
import java.io.Closeable;

public class TestBean implements /*Closeable */ AutoCloseable/*, DisposableBean*/{
    public TestBean() {
        System.out.println(getClass());
    }

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println(this + ": preDestroy()");
    }

    public void close(){
        System.out.println(this + ": close()");

    }


    public void destroyMethod(){
        System.out.println(this + ": destroyMethod()");

    }

    public void destroy() throws Exception {
        System.out.println(this + ": destroy()");

    }
}
