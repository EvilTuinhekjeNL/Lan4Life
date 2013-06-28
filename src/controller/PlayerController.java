package controller;

import java.util.ArrayList;

import model.Game;
import model.Player;

public class PlayerController {
	private ArrayList<Player> players;
	public static PlayerController instance;

	public static PlayerController getInstance(){
		if (instance == null)
			instance = new PlayerController();
		return instance;
	}
	
	private PlayerController() {
		
	}

	public void setGames(ArrayList<Game> games) {
		addDefaultPlayers(games);
	}

	private void addDefaultPlayers(ArrayList<Game> games) {
		String[] names = new String[] { "Ruben", "Marcus", "Tim", "Stan",
				"Eloy", "Stefan", "David", "Rob" };
		players = new ArrayList<Player>();

		for (int i = 0; i < names.length; i++) {
			Player newPlayer = new Player(names[i], games);
			players.add(newPlayer);
		}
	}

	public int getCountPlayers() {
		return players.size();
	}

	public int getCountActivePlayers() {
		int active = 0;
		for (Player player : players) {
			if (player.isActive())
				active++;
		}
		return active;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

}
