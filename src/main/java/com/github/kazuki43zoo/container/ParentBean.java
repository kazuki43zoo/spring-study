package com.github.kazuki43zoo.container;

public class ParentBean {

    private ChildBean childBean;

    public ParentBean() {

    }

    public ParentBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName() + " with " + childBean.getClass().getSimpleName();
    }

}
