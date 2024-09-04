package com.kodilla.beanlifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/create-bean")
    public String createBean() {
        MyBean myBean = context.getBean(MyBean.class);
        return "Bean created: " + myBean;
    }
    @Bean
    @Scope("prototype")
    public MyBean myBean() {
        return new MyBean();
    }
}
