package com.github.kazuki43zoo.container;

public class ParentMultiBean {

    private ChildBean childBean;
    private ChildBean2 childBean2;

    public ParentMultiBean(){
        System.out.println(getClass());
    }

    public ParentMultiBean(ChildBean childBean, ChildBean2 childBean2) {
        System.out.println(getClass());
        this.childBean = childBean;
        this.childBean2 = childBean2;
    }

    public void setChildBean(ChildBean childBean) {
        System.out.println("setChildBean");
        this.childBean = childBean;
    }

    public void setChildBean2(ChildBean2 childBean2) {
        System.out.println("setChildBean2");
        this.childBean2 = childBean2;
    }

    public String getMessage() {
        return "Hello World by " + getClass().getSimpleName() + " with " + childBean.getClass().getSimpleName() + " and " + childBean2.getClass().getSimpleName();
    }

}
