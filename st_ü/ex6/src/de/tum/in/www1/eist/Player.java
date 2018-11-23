package de.tum.in.www1.eist;

import java.util.*;

public class Player {
	private String name;
	private List games;

	public Player() {
		games=new ArrayList();
	}

	public void addGame(Game g) {
		if(!games.contains(g)) {
			games.add(g);
			g.addPlayer(this);
		}
	}

	public void removeGame(Game g) {
		games.remove(g);
	}

	public List getGames() {
		return games;
	}

	public String getName() {
		return name;
	}

	public void setName(String s) {
		name=s;
	}
}
