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
		return rounds.get(rounds.size()-1);
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
