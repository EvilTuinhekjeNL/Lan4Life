package test;

import controller.GameController;

public class GameControllerTest {
	public static void main(String[] args) {
		GameController gc = GameController.getInstance();
		System.out.println(gc.getGames());
	}
}
