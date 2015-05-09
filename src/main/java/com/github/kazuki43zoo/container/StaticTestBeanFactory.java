package com.github.kazuki43zoo.container;

public class StaticTestBeanFactory {

    private StaticTestBeanFactory(){

    }

    public static TestBean createTestBean(){
        return new TestBean();
    }

}
