package com.github.kazuki43zoo.container;

public class StringStore implements Store<String> {

    @Override
    public String get() {
        return "test";
    }
}
