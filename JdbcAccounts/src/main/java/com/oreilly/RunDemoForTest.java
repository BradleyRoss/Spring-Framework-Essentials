package com.oreilly;

import com.oreilly.config.AppConfig;
import com.oreilly.repositories.AccountRepository;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.AbstractDataSource;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
/**
 * Test driver.
 * 
 * <p>Changes</p>
 * <ul>
 * <li>Changed {@link ApplicationContext} to {@link AbstractApplicationContext}</li>
 * <li>Added import statement for AbstractApplicationContext</li>
 * <li>Closed context at end of program</li>
 * <li>AppConfig.class was specified twice for creation of context.  I removed one
 *     of the arguments.</li>
 * </ul>
 * <p>{@link BeansException} is a subclass of {@link RuntimeException}.</p>
 * @see AnnotationConfigApplicationContext
 * @see AbstractApplicationContext
 * 
 *
 */
public class RunDemoForTest {

	public static void main(String[] args) {
		/*
		 *        AbstractApplicationContext context =
		 *             new AnnotationConfigApplicationContext(
		 *                     AppConfig.class, AppConfig.class);
		 */
		try {
			AbstractApplicationContext context =
					new AnnotationConfigApplicationContext(AppConfig.class);
			try {
				context.getBean("jdbcAccountRepository");
			} catch (BeansException e) {
				e.printStackTrace();
			}
			System.out.println("Beans");
			System.out.println(context.getBeanDefinitionCount());
			for (String name : context.getBeanDefinitionNames()) {
				System.out.println(name);
			}
			System.out.println("Beans of type DataSource");
			for (String name : context.getBeanNamesForType(DataSource.class)) {
				System.out.println(name);
			}
			context.close();
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
