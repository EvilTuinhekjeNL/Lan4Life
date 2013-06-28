package model;

public class Round {
	public static int getTotalSkill(Player[] team, String game) {
		int total = 0;
		for (Player p : team)
			total += p.getSkill().get(game);
		return total;
	}
	
	private Player[]	teamA;
	private Player[]	teamB;
	// This is weird before the round ends, so don't fuck around with it until
	// it does :D
	private Game		playing;
	
	private boolean		teamAWon;
	
	public Round(Game playing, Player[] teamA, Player[] teamB) {
		this.playing = playing;
		this.teamA = teamA;
		this.teamB = teamB;
	}
	
	public Game getPlaying() {
		return playing;
	}
	
	public Player[] getTeamA() {
		return teamA;
	}
	
	public int getTeamATotalSkill() {
		return getTotalSkill(teamA, getPlaying().getName());
	}
	
	public Player[] getTeamB() {
		return teamB;
	}
	
	public int getTeamBTotalSkill() {
		return getTotalSkill(teamB, getPlaying().getName());
	}
	
	public void setTeamAWin() {
		teamAWon = true;
	}
	
	public void setTeamBWin() {
		teamAWon = false;
	}
	
	public boolean teamAWon() {
		return teamAWon;
	}
}
