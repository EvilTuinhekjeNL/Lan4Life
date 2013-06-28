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
	
	private Player findPlayer(String name) {
		for (Player p : players)
			if (p.getName().toLowerCase().equals(name.toLowerCase())) return p;
		
		return null;
	}
	
	private int findPlayerIndex(String name) {
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			if (p.getName().toLowerCase().equals(name.toLowerCase())) return i;
		}
		return 0;
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
	
	// number of rounds played
	public int getPlayed(String name) {
		Player p = findPlayer(name);
		if (p == null) return 0; // TODO: make this
		return 1;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public int getRank(String name) {
		return findPlayerIndex(name);
	}
	
	public int getWon(String name) {
		Player p = findPlayer(name);
		// TODO: Make this work
		// NOTE: amount of times won
		if (p == null) return 0;
		return 1;
	}
	
	public void setGames(ArrayList<Game> games) {
		addDefaultPlayers(games);
	}
	
	private void sortPlayers() {
		boolean didSomething = false;
		if (players.size() >= 2) for (int i = 0; i < players.size()-1; i++)
			if (players.get(i).getScore() > players.get(i + 1).getScore()) {
				Player a = players.get(i);
				Player b = players.get(i + 1);
				players.set(i, b);
				players.set(i + 1, b);
				didSomething = true;
			}
		if (didSomething) sortPlayers();
	}
}
