package com.djdduty.LD26.game;

import org.lwjgl.input.Keyboard;

import engine.JWolf2D.core.Screen;
import engine.JWolf2D.core.ScreenManager;
import engine.JWolf2D.geom.Rectangle2;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.graphic.Sprite;
import engine.JWolf2D.gui.GuiManager;
import engine.JWolf2D.gui.Label;
import engine.JWolf2D.level.Level;

public class TeaTimeLevel{
	private Level level;
	private Sir player;
	private int xoff, yoff;
	private Tea tea;
	public boolean victory=false,failure=false;
	private GuiManager gui;
	private Label distance, travelled;
	private int travelledDist, maxDistance=100;
	private Sprite hud;
	public String levelPath;
	
	public TeaTimeLevel(Vector2 teaPos, Vector2 playerPos, String levelPath, int maxDistance) {
		level = new Level("level");
		level.load(levelPath);
		this.levelPath = levelPath;
		
		this.maxDistance = maxDistance;
		
		tea = new Tea(teaPos);
		player = new Sir(playerPos, level.getMainLayer());
		
		gui = new GuiManager("res/images/gui/fontBig.png");
		distance = new Label(gui, "Distance: ", new Vector2(10,10), 18, 18);
		distance.setColor(1,1,1,1);
		gui.addLabel(distance);
		travelled = new Label(gui, "Travelled: ", new Vector2(10, 30), 18, 18);
		travelled.setColor(1,1,1,1);
		gui.addLabel(travelled);
		
		hud = new Sprite(new Vector2(0, 0), "hud");
	}

	public void update(long deltaTime) {
		travelledDist = player.getDistanceTravelled()/32;
		
		if(!victory && !failure) {
			level.update(deltaTime);
			player.update(deltaTime);
			xoff = (int) (player.getPos().x-368)*-1;
			yoff = (int) ((player.getPos().y-268)*-1);
		}
		
		if(tea.collides(new Rectangle2(player.getPos(), new Vector2(64, 64)))) {
			victory = true;
		}
		
		if(travelledDist >= maxDistance) failure = true;
		
		distance.setContent("Distance To Tea:" + String.valueOf(Math.round(tea.getPos().distance(player.getPos())/32)) + "m");
		travelled.setContent("Distance Gone:" + String.valueOf(travelledDist) + "m" + " Max:" + String.valueOf(maxDistance) + "m");
	}
	
	public void reset() {
		travelledDist = 0;
		failure = false;
		player = new Sir(new Vector2(400-32,300-32), level.getMainLayer());
	}

	public void render() {
		if(!victory && !failure) {
			level.render(xoff, yoff);
			player.render(xoff, yoff);
			tea.render(xoff, yoff);
			hud.render(0, 0);
			gui.render();
		}
	}

	public void clear() {
		level.clear();
	}
	
	public boolean getStarted() {
		return false;
	}
}
