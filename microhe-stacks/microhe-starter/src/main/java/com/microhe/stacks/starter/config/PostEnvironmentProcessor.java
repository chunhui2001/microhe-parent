package com.microhe.stacks.starter.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
//@Order(Ordered.LOWEST_PRECEDENCE)
public class PostEnvironmentProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
            SpringApplication application) {
        // TODO Auto-generated method stub
        System.out.println(111);
    }

}
