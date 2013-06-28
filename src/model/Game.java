package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Game {
	private String name;
	
	public Game(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Element toXML(Document d) {
		Element me = d.createElement("Game");
		me.setAttribute("Name", name);
		return me;
	}
}
