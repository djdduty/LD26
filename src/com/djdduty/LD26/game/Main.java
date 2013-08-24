package com.djdduty.LD26.game;

import engine.JWolf2D.core.Game;

public class Main {
	public static void main(String[] args) throws Exception {
		Game game = new Game(new MenuScreen());
		game.setTitle("LD26!");
		game.setDisplayMode(800, 600);
		game.start();
	}
}
