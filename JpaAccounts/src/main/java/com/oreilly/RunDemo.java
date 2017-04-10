package com.oreilly;

import com.oreilly.config.AppConfig;
import com.oreilly.repositories.AccountRepository;

// import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
/**
 * 
 * Table driver.
 * 
 * <p>The table account should not exist in the database
 *    before running this program.</p>
 *
 */
public class RunDemo {
    public static void main(String[] args) {
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(
                        AppConfig.class, AppConfig.class);
        System.out.println(context.getBeanDefinitionCount());
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        System.out.println("Looking for beans of type AccountRepository");
        String[] beanNames = context.getBeanNamesForType(AccountRepository.class);
        if (beanNames.length == 0) {
        	System.out.println("     No beans found");
        } else {
        	for (String name : beanNames) {
        		System.out.println("     " + name);
        	}
        }
        context.close();
    }
}
