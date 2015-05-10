package com.github.kazuki43zoo.container;

/**
 * Created by shimizukazuki on 2015/05/10.
 */
public class Hierarchy2Bean {
    private Hierarchy3Bean hierarchy3Bean = new Hierarchy3Bean();

    public Hierarchy3Bean getHierarchy3Bean() {
        return hierarchy3Bean;
    }

    public void setHierarchy3Bean(Hierarchy3Bean hierarchy3Bean) {
        this.hierarchy3Bean = hierarchy3Bean;
    }
}
