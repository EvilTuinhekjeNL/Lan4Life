package model;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Player {
	private String name;
	private boolean active;
	private int score;
	private HashMap<String, Integer> skill;
	
	public Player(String name, ArrayList<Game> gameList) {
		active = true;
		skill = new HashMap<String, Integer>();
		this.name = name;
		for (Game game : gameList)
			setSkill(game.getName(), 1);
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public HashMap<String, Integer> getSkill() {
		return skill;
	}
	
	public void incrementScore(int increment) {
		score += increment;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setScore(int newScore) {
		score = newScore;
	}
	
	public void setSkill(String key, int value) {
		skill.put(key, value);
	}
	
	@Override
	public String toString() {
		return "" + name + ", score: " + score;
	}
	
	public Element toXML(Document d) {
		Element me = d.createElement("Player");
		
		me.setAttribute("Name", name);
		me.setAttribute("Score", Integer.toString(score));
		
		for(String key : skill.keySet()) {
			Element skillXML = d.createElement("Skill");
			skillXML.setAttribute("Key", key);
			skillXML.setAttribute("Value", Integer.toString(skill.get(key)));
			me.appendChild(skillXML);
		}
		
		return me;
	}
}
