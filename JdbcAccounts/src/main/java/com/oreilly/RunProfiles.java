package com.oreilly;

import com.oreilly.config.AppConfig;
// import com.oreilly.repositories.AccountRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import org.springframework.context.annotation.Profile;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
// import org.springframework.jdbc.datasource.AbstractDataSource;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
// import org.apache.commons.dbcp2.BasicDataSource;
/**
 * Test driver using multiple profiles.
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
public class RunProfiles {
	/**
	 * Profile property.
	 */
	protected String profile = "prod";
	/**
	 * Spring application context.
	 */
	protected AbstractApplicationContext context = null;
	/**
	 * Getter for Profile property.
	 * @return value of profile
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * Setter for Profile property.
	 * @param value value for profile
	 */
	public void setProfile(String value) {
		profile = value;
	}
	/**
	 * Creates the application context and runs some operations.
	 */
	public void run() {
		System.setProperty("spring.profiles.active", profile);
		System.out.println("Using " + profile + " profile");
		try {
			context =
					new AnnotationConfigApplicationContext(AppConfig.class);
			System.out.println("There are " + context.getBeanDefinitionCount() + " beans");
			for (String name : context.getBeanDefinitionNames()) {
				System.out.println("     " + name);
			}
			/**
			 * This will list all beans that are implementations
			 * of the DataSource interface.
			 */
			System.out.println();
			System.out.println("Beans of type DataSource");
			String[] beanList = context.getBeanNamesForType(DataSource.class);
			if (beanList == null || beanList.length == 0) {
				System.out.println("     No beans found");
			} else {
				for (String name : beanList) {
					DataSource temp = context.getBean(name, DataSource.class);
					System.out.println("     " + name + " : " + temp.getClass().getName());
				}
			}
			System.out.println();
			System.out.println("Looking for nonexistent bean");
			try {
				context.getBean("xxx");
			} catch (BeansException e) {
				System.out.println("Inner try/catch block");
				e.printStackTrace();
				throw e;
			}
		} catch (BeansException e) {
			System.out.println("Outer try/catch block");
			e.printStackTrace();
		} finally {
			context.close();
		}
	}
	/**
	 * Test driver
	 * @param args if arguments are used, they are the list of
	 *     profiles to be tested.  The default is to test
	 *     dev, test, and prod profiles.
	 */
	public static void main(String[] args) {
		RunProfiles instance = new RunProfiles();
		List<String> profiles = new ArrayList<String>();
		if (args.length == 0) {
			profiles.add("dev");
			profiles.add("test");
			profiles.add("prod");
		} else {
			for (String item : args) {
				profiles.add(item);
			}
		}
		for (String item : profiles) {
			instance.setProfile(item);
			instance.run();
			printSeparator();
		}
	}
	/**
	 * Convenience class for placing a separator in the output.
	 */
	public static void printSeparator() {
		for (int i = 0 ; i < 3; i++) {
			System.out.println("***  ***  ***  ***  ***  ***  ***  ***  ***  ***");
		}
	}
}
