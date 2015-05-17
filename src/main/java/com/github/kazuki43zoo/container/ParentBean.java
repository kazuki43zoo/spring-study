package com.github.kazuki43zoo.container;

public class ParentBean {

    private ChildBean childBean;

    public ParentBean() {
        System.out.println("default:" + getClass());
    }

    public ParentBean(ChildBean childBean) {
        System.out.println("ChildBean : " + getClass());
        this.childBean = childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public ChildBean getChildBean() {
        return childBean;
    }

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName() + " with " + childBean.getClass().getSimpleName();
    }

}
