package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;


public class BeanInheritanceTest {

    @Test
    public void configureUsingJavaXml() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContextBeanInheritance.xml");
        context.registerShutdownHook();

        JdbcTemplate jdbcTemplate = context.getBean("orderJdbcTemplate", JdbcTemplate.class);
        Date date = jdbcTemplate.queryForObject("SELECT CURRENT_TIMESTAMP", Date.class);
        System.out.println(date);

    }

}
