package de.tum.in.www1.eist;

import java.util.*;

public class Game {
	private String name;
	private List players;

	public Game() {
		players=new ArrayList();
	}

	public void addPlayer(Player p) {
		if(!players.contains(p)) {
			players.add(p);
			p.addGame(this);
		}
	}

	public List getPlayers() {
		return players;
	}

	public void removePlayer(Player p) {
		players.remove(p);
	}

	public static void start() {
	}

	public static void stop() {
	}

	public String getName() {
		return name;
	}

	public void setName(String s) {
		name=s;
	}
}
