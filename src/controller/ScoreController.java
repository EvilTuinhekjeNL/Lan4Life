package controller;

import java.util.ArrayList;
import java.util.Random;

import model.Game;
import model.Player;
import model.Round;

public class ScoreController {
	private ArrayList<Round> rounds;
	
	private static ScoreController instance;
	
	public static ScoreController getInstance() {
		if (instance == null)
			instance = new ScoreController();
		return instance;
	}
	
	private ScoreController() {
		rounds = new ArrayList<Round>();
	}
	
	private Player[][] distribute(ArrayList<Player> remaining,
			ArrayList<Player> teamA, ArrayList<Player> teamB, String game) {
		if (remaining.size() == 0) {
			Player[][] current = new Player[][] {teamA.toArray(new Player[0]), teamB.toArray(new Player[0])};
			return current;
		} else {
			Player addMe = remaining.get(0);
			remaining.remove(0);
			if (Round.getTotalSkill(teamB.toArray(new Player[0]), game) > Round
					.getTotalSkill(teamA.toArray(new Player[0]), game)) teamA
					.add(addMe);
			else
				teamB.add(addMe);
			return distribute(remaining, teamA, teamB, game);
		}
	}
	
	private Player[][] distribute(ArrayList<Player> remaining, String game) {
		ArrayList<Player> teamA = new ArrayList<Player>();
		ArrayList<Player> teamB = new ArrayList<Player>();
		// TODO: sort remaining by current game skill
		return distribute(remaining, teamA, teamB, game);
	}
	
	public int getCountRounds() {
		return rounds.size() > 0 ? 1 : rounds.size();
	}
	
	public Game getCurrentGame() {
		return getLatest().getPlaying();
	}
	
	public Player[] getCurrentTeamA() {
		return getLatest().getTeamA();
	}
	
	public Player[] getCurrentTeamB() {
		return getLatest().getTeamB();
	}
	
	public Round getLatest() {
		if (rounds.size() > 0) return rounds.get(rounds.size() - 1);
		else
			return new Round(GameController.getInstance().getGames().get(0),
					new Player[0], new Player[0]);
	}
	
	public String getLeader() {
		Player highest = PlayerController.getInstance().getPlayers().get(0);
		for (Player candidate : PlayerController.getInstance().getPlayers())
			if (candidate.getScore() > highest.getScore()) highest = candidate;
		return highest.getName();
	}
	
	public void newGame() {
		// TODO: Make this work
		// Random games
		// Random (fair teams)
		// rounds.add(new Round(g));
		GameController c = GameController.getInstance();
		Random r = new Random();
		Game g = c.getGames().get(r.nextInt(c.getGames().size()));
		// TODO: Pass players sorted by current game skill
		ArrayList<Player> players = PlayerController.getInstance().getPlayers();
		Player[][] teams = this.distribute(players, g.getName());
		
		rounds.add(new Round(g, teams[0], teams[1]));
	}
	
	public void setTeamAWin(boolean b) {
		if (rounds.size() > 0) {
			Round last = rounds.get(rounds.size() -1);
			if (b) last.setTeamAWin();
			else
				last.setTeamBWin();
		}
	}
}
