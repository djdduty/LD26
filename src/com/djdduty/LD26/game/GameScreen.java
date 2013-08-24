package com.djdduty.LD26.game;

import org.lwjgl.input.Keyboard;

import engine.JWolf2D.core.Screen;
import engine.JWolf2D.core.ScreenManager;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.graphic.Sprite;
import engine.JWolf2D.gui.GuiManager;
import engine.JWolf2D.gui.Label;

public class GameScreen implements Screen {
	private TeaTimeLevel level;
	private Sprite victory, failure, end;
	private Screen menu;
	private ScreenManager manager;
	private boolean started, finished=false;
	private GuiManager gui;
	
	public GameScreen(Screen menu) {
		this.menu = menu;
	}
	
	public void init(ScreenManager manager) {
		this.manager = manager;
		gui = new GuiManager("res/images/gui/fontBig.png");
		
		level = new TeaTimeLevel(new Vector2(1280, -384), new Vector2(368, 268), "res/levels/level1.xml", 60);
		victory = new Sprite(new Vector2(0,0), "victory");
		failure = new Sprite(new Vector2(0,0), "failure");
		end = new Sprite(new Vector2(0,0), "about");
		
		gui.addLabel(new Label(gui, "Congratulations, you won!!", new Vector2(400-(30*13), 100), 30, 32));
		gui.addLabel(new Label(gui, "I would tip my hat to you, good sir,", new Vector2(400-(18*18), 300), 18, 18));
		gui.addLabel(new Label(gui, "but the guy who made this game can't art", new Vector2(400-(18*20), 324), 18, 18));
		gui.addLabel(new Label(gui, "Press space to return to the main menu", new Vector2(400-(18*19), 500), 18, 18));
	}

	public void update(long deltaTime) {
		level.update(deltaTime);
		
		if(level.victory && level.levelPath == "res/levels/level1.xml") {
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				level = new TeaTimeLevel(new Vector2(384, 960), new Vector2(368, 268), "res/levels/level2.xml", 120);
		}
		
		if(level.victory && level.levelPath == "res/levels/level2.xml") {
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				level = new TeaTimeLevel(new Vector2(1855, 180), new Vector2(368, 268), "res/levels/level3.xml", 150);
		}
		
		if(level.victory && level.levelPath == "res/levels/level3.xml") {
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				level = new TeaTimeLevel(new Vector2(190, 896), new Vector2(368, 268), "res/levels/level4.xml", 250);
		}
		
		if(level.victory && level.levelPath == "res/levels/level4.xml") {
				finished = true;
				level.clear();
				if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
					manager.setScreen(menu);
		}
		
		if(level.failure) {
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
				level.reset();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			manager.setScreen(menu);
		
		started = true;
	}

	public void render() {
		level.render();
		
		if(level.victory)
			victory.render(0, 0);
		if(level.failure)
			failure.render(0, 0);
		if(finished) {
			end.render(0, 0);
			gui.render();
		}
	}

	public boolean getStarted() {
		return started;
	}

}
