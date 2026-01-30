package com.example.BillingApp.Controllers;

import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    @Autowired
    private MyDependency myDependency;

    private String beanName;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("Bean name is set to " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        System.out.println("BeanFactory has been set.");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        System.out.println("ApplicationContext has been set.");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Bean is going through afterPropertiesSet.");
    }

    public void customInit() {
        System.out.println("Custom init method.");
    }

    @Override
    public void destroy() {
        System.out.println("Bean will be destroyed now.");
    }

    public void customDestroy() {
        System.out.println("Custom destroy method.");
    }
}
