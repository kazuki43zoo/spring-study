package com.github.kazuki43zoo.container;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionsBean {

    private List<TestBean> list;
    private Set<String> set;
    private Map<String,TestBean> map;
    private Properties properties;

    public List<TestBean> getList() {
        return list;
    }

    public void setList(List<TestBean> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, TestBean> getMap() {
        return map;
    }

    public void setMap(Map<String, TestBean> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
