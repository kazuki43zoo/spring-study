package com.github.kazuki43zoo.container;

public class Hierarchy1Bean {
    private Hierarchy2Bean hierarchy2Bean = new Hierarchy2Bean();

    public Hierarchy2Bean getHierarchy2Bean() {
        return hierarchy2Bean;
    }

    public void setHierarchy2Bean(Hierarchy2Bean hierarchy2Bean) {
        this.hierarchy2Bean = hierarchy2Bean;
    }
}
