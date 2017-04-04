package com.oreilly;

import com.oreilly.config.AppConfig;
import com.oreilly.entities.Game;
import com.oreilly.entities.Team;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * Runs the code for the baseball games using the 
 * Spring framework.
 * 
 *
 */
public class RunDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AppConfig.class);

		Game game = context.getBean("game", Game.class);
		Team royals = context.getBean("royals", Team.class);
		Team redSox = context.getBean("redSox", Team.class);
		Team cubs = context.getBean("cubs", Team.class);

		game.setHomeTeam(royals);
		game.setAwayTeam(cubs);
		game.playGame();
		for (int i = 0; i < 2; i++) {
			System.out.println("***  ***  ***  ***  ***  ***  ***  ***  ***");
			game.setHomeTeam(cubs);
			game.setAwayTeam(redSox);
			game.playGame();
		}
		
		context.close();
	}
}
