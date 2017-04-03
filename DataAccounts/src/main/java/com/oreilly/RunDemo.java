package com.oreilly;

import com.oreilly.config.AppConfig;
// import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class RunDemo {
    public static void main(String[] args) {
    	System.out.println("DataAccounts (Hibernate) example"); 
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfig.class, AppConfig.class);
        System.out.println(context.getBeanDefinitionCount());
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.close();
    }
}
