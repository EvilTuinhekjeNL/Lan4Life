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
		players = new ArrayList<Player>();
	}
	
	private void addDefaultPlayers(ArrayList<Game> games) {
		String[] names = new String[] { "Ruben", "Marcus", "Tim", "Stan",
				"Eloy", "Stefan", "David", "Rob" };
		
		addPlayers(names);
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	private void addPlayers(String[] peopleNames) {
		for (String name : peopleNames)
			addPlayer(new Player(name, GameController.getInstance()
					.getGames()));
	}
	
	public int getCountActivePlayers() {
		int active = 0;
		for (Player player : players)
			if (player.isActive())
				active++;
		return active;
	}
	
	public int getCountPlayers() {
		return players.size();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setGames(ArrayList<Game> games) {
		addDefaultPlayers(games);
	}
	
}
