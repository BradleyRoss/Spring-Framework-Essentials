package com.oreilly;

import com.oreilly.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import org.springframework.context.annotation.Profile;
import org.springframework.context.support.AbstractApplicationContext;
// import org.springframework.jdbc.datasource.AbstractDataSource;
import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
/**
 * Test driver.
 * 
 * <p>Changes</p>
 * <ul>
 * <li>Changed {@link ApplicationContext} to {@link AbstractApplicationContext}</li>
 * <li>Added import statement for AbstractApplicationContext</li>
 * <li>Closed context at end of program</li>
 * </ul>
 * @see AnnotationConfigApplicationContext
 *
 */
public class RunDemo implements Runnable {
	protected List<String> profileList = new ArrayList<String>();
	protected void setProfiles(List<String> values) {
		profileList = values;
	}
	protected List<String> getProfiles() {
		return profileList;
	}
	public void run() {
		if (profileList.size() == 0) {
			profileList.add("prod");
		}
		for (String item : profileList) {
			runCase(item);
			for (int i = 0; i < 2; i++) {
				System.out.println("*** *** *** *** *** *** *** ***");
			}
		}
	}
	protected void runCase(String profile) {
		System.setProperty("spring.profiles.active", profile);
		System.out.println("Running profile " + profile);
		AbstractApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);

		System.out.println("There are " + 
				context.getBeanDefinitionCount() + " beans");
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println("     " + name);
		}
		System.out.println("Looking for beans implementing DataSource");
		String[] beanList = context.getBeanNamesForType(DataSource.class);
		if (beanList.length == 0) {
			System.out.println("     No beans found");
		} else {
			for (String item : beanList) {
				System.out.println("     " + item);
			}
		}
		context.close();
	}
	public static void main(String[] args) {
		List<String> profiles = new ArrayList<String>();
		profiles.add("prod");
		profiles.add("test");
		profiles.add("dev");
		RunDemo instance = new RunDemo();
		instance.setProfiles(profiles);
		instance.run();
	}
}
