package controller;

import java.util.ArrayList;

import model.Game;

public class GameController {
	private ArrayList<Game> games;
	private static GameController instance;
	
	public static GameController getInstance() {
		if (instance == null)
			instance = new GameController();
		return instance;
	}
	
	private GameController() {
		games = new ArrayList<Game>();
	}
	
	protected Game getGame(String name) {
		for(Game g : games)
			if (g.getName().toLowerCase().equals(name.toLowerCase()))
				return g;
		return null;
	}
	
	public ArrayList<Game> getGames() {
		if (games.size() == 0) init();
		return games;
	}
	
	protected void init() {
		String[] gameNames = new String[] { "Warcraft 3",
				"Call of Duty 4: Modern Warfare",
				"Command and Conquer 3: Tiberium Wars", "League of Legends",
				"Sid Meier's: Civilization V", "Left4Dead2" };
		for (String gameName : gameNames) {
			Game newGame = new Game(gameName);
			games.add(newGame);
		}
	}
}
