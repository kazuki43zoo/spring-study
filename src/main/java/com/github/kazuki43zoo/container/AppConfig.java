package com.github.kazuki43zoo.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    TestBean testBean() {
        return new TestBean();
    }

}
