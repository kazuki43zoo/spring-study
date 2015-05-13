package com.github.kazuki43zoo.container;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

import java.util.Arrays;

public class DebugBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Arrays.asList(beanFactory.getBeanDefinitionNames()).forEach((beanName) -> {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            System.out.println("----------" + beanName + "----------");
            System.out.println("beanClass : " + beanDefinition.getBeanClassName());
            for (PropertyValue value : beanDefinition.getPropertyValues().getPropertyValueList()) {
                System.out.println(value.getName() + " : " + value.getValue());
            }
        });
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}

