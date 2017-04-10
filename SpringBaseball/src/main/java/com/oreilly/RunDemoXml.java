package com.oreilly;

import com.oreilly.entities.Game;
// import com.oreilly.entities.BaseballGame;
import com.oreilly.entities.Team;
// import com.oreilly.entities.BaseballTeam;
// import com.oreilly.entities.Cubs;
// import com.oreilly.entities.Royals;
// import com.oreilly.entities.RedSox;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * This is a demonstration of the baseball game using Spring and XML 
 * configuration..
 * 
 * 
 * <p>{@link AbstractApplicationContext} is {@link java.io.Closeable}
 *    and {@link AutoCloseable}.
 *    {@link ApplicationContext} is not Closeable.  (Example from Safari
 *    used Application Context.  I changed it to 
 *    AbstractApplicationContext and added call to close method.</p>
 * 
 * 
 * <p>You can also use both XML and annotations when using 
 *    ClassPathXmlApplicationContext,
 *    but the bean definitions will overwrite each other if they disagree..</p>
 * 
 * @see ApplicationContext
 * @see AnnotationConfigApplicationContext
 * @see ClassPathXmlApplicationContext
 * @see RunDemoWithoutSpring
 * 
 *
 */
public class RunDemoXml {
	public static void main(String[] args ) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Team redSox = context.getBean("team", Team.class);
		redSox.setName("Boston Red Sox");
		Team reds = context.getBean("team", Team.class);
		reds.setName("Cincinnatti Reds");
		Game game = context.getBean("game", Game.class);
		game.setAwayTeam(reds);
		game.setHomeTeam(redSox);

		System.out.println("There are " + context.getBeanDefinitionCount() + " bean definitions");
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println("     Bean " + name);
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(game.playGame() + " won");
		}
		context.close();
	}
}


