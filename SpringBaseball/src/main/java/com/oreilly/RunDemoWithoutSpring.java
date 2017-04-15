package com.oreilly;

import com.oreilly.entities.Game;
import com.oreilly.entities.Team;
import com.oreilly.entities.Cubs;
import com.oreilly.entities.RedSox;
import com.oreilly.entities.BaseballGame;
/**
 * Runs the code for the baseball games without
 * using the Spring framework.
 * 
 * <p>This code directly uses the classes that are
 *    used in the Spring framework to create the beans.</p>
 * 
 *
 */
public class RunDemoWithoutSpring {
	public static void main(String[] args) {
		Team redSox = new RedSox();
		Team cubs = new Cubs();
		Game game = new BaseballGame(redSox, cubs);
		for (int i = 0; i < 4; i++) {
			System.out.println(game.playGame());
		}
	}
}
