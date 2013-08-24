package com.djdduty.LD26.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engine.JWolf2D.core.Screen;
import engine.JWolf2D.core.ScreenManager;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.graphic.AnimatedSprite;
import engine.JWolf2D.graphic.Sprite;
import engine.JWolf2D.gui.Button;
import engine.JWolf2D.gui.GuiManager;
import engine.JWolf2D.gui.Label;
import engine.JWolf2D.resource.Textures;

public class AboutScreen implements Screen{
	private Screen menu;
	private ScreenManager manager;
	private boolean started=false;
	private Sprite about;
	private AnimatedSprite titleSir;
	private int timer, fx;
	private GuiManager gui;
	private Button back;
	
	public AboutScreen(Screen menu) {
		this.menu = menu;
	}
	
	public void init(ScreenManager manager) {
		this.manager = manager;
		gui = new GuiManager("res/images/gui/fontBig.png");
		
		about = new Sprite(new Vector2(0,0), "about");
		
		titleSir = new AnimatedSprite("titlesir", 208, 400, new Vector2(550, 150));
		
		gui.addLabel(new Label(gui, "About: ", new Vector2(10, 50), 64, 64));
		gui.addLabel(new Label(gui, "A game made in less than 48", new Vector2(30, 150), 18, 18));
		gui.addLabel(new Label(gui, "hours by me, djdduty, for the", new Vector2(30, 174), 18, 18));
		gui.addLabel(new Label(gui, "Ludum Dare 26...", new Vector2(30, 198), 18, 18));
		gui.addLabel(new Label(gui, "You play as a minimalist", new Vector2(30, 222), 18, 18));
		gui.addLabel(new Label(gui, "Sir, who is simply trying", new Vector2(30, 246), 18, 18));
		gui.addLabel(new Label(gui, "to find his tea, amongst", new Vector2(30, 270), 18, 18));
		gui.addLabel(new Label(gui, "his uncountable books!", new Vector2(30, 294), 18, 18));
		gui.addLabel(new Label(gui, "You want to move as", new Vector2(30, 318), 18, 18));
		gui.addLabel(new Label(gui, "Minimal as possible, or", new Vector2(30, 342), 18, 18));
		gui.addLabel(new Label(gui, "else you will get lost,", new Vector2(30, 366), 18, 18));
		gui.addLabel(new Label(gui, "and your tea will go cold!", new Vector2(30, 390), 18, 18));
		
		back = new Button(new Vector2(30, 500), gui, "Back", "button", "buttonDown");
		gui.addButton(back);
	}

	public void update(long deltaTime) {
		while(Mouse.next()) {
			gui.update();
		}
		if(back.clicked()) {
			manager.setScreen(menu);
		}
	}

	public void render() {
		about.render(0,0);
		timer++;
		if(timer >= 10) {
			timer = 0;
			if(fx < 3) {
				fx++;
			}else {
				fx = 0;
			}
		}
		titleSir.render(fx, 0, "left", 0, 0);
		gui.render();
	}
	@Override
	public boolean getStarted() {
		// TODO Auto-generated method stub
		return started;
	}

}
