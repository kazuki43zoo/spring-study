package com.github.kazuki43zoo.container;

public class ChildBean2 {

    public ChildBean2(){
        System.out.println(getClass());
    }

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName();
    }
}
