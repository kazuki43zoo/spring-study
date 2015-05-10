package com.github.kazuki43zoo.container;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsBean2 {

    private List<ChildBean> list;
    private Set<ChildBean> set;
    private Map<String, ChildBean> map;
    private ChildBean[] array;

    public List<ChildBean> getList() {
        return list;
    }

    public void setList(List<ChildBean> list) {
        this.list = list;
    }

    public Set<ChildBean> getSet() {
        return set;
    }

    public void setSet(Set<ChildBean> set) {
        this.set = set;
    }

    public Map<String, ChildBean> getMap() {
        return map;
    }

    public void setMap(Map<String, ChildBean> map) {
        this.map = map;
    }

    public ChildBean[] getArray() {
        return array;
    }

    public void setArray(ChildBean[] array) {
        this.array = array;
    }
}
