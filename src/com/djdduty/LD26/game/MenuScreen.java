package com.djdduty.LD26.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import engine.JWolf2D.core.Screen;
import engine.JWolf2D.core.ScreenManager;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.graphic.AnimatedSprite;
import engine.JWolf2D.graphic.Sprite;
import engine.JWolf2D.gui.Button;
import engine.JWolf2D.gui.GuiManager;
import engine.JWolf2D.gui.Label;
import engine.JWolf2D.resource.Textures;

public class MenuScreen implements Screen{
	private GuiManager gui;
	private ScreenManager manager;
	private Button play, about, exit;
	private Sprite logo, background;
	private float logoTimer=4, fadeIn, fadeOut = 2;
	private boolean started = false;
	private long deltaTime;
	private AnimatedSprite titleSir;
	private int timer, fx;
	private Screen gameScreen;
	
	public void init(ScreenManager manager) {
		this.manager = manager;
		gui = new GuiManager("res/images/gui/fontBig.png");//takes path to font image
		
		//load resources
		System.out.println("loading resources...");
		Textures.get().add("button", "res/images/gui/button.png");
		Textures.get().add("buttonDown", "res/images/gui/buttonDown.png");
		Textures.get().add("bookshelfDown", "res/images/level/bookshelf.png");
		Textures.get().add("bookshelfUp", "res/images/level/bookshelfUp.png");
		Textures.get().add("bookshelfLeft", "res/images/level/bookshelfLeft.png");
		Textures.get().add("bookshelfRight", "res/images/level/bookshelfRight.png");
		Textures.get().add("bookshelfLeftUp", "res/images/level/bookshelfCornerLU.png");
		Textures.get().add("bookshelfRightUp", "res/images/level/bookshelfCornerRU.png");
		Textures.get().add("bookshelfR", "res/images/level/bookshelfCornerR.png");
		Textures.get().add("bookshelfL", "res/images/level/bookshelfCornerL.png");
		Textures.get().add("tea", "res/images/level/tea.png");
		Textures.get().add("floor", "res/images/level/floor.png");
		Textures.get().add("sir", "res/images/playerbig.png");
		Textures.get().add("victory", "res/images/victory.png");
		Textures.get().add("failure", "res/images/failure.png");
		Textures.get().add("hud", "res/images/gui/hud.png");
		Textures.get().add("about", "res/images/about.png");
		//done loading resources
		
		//set up logo
		Textures.get().add("logo", "res/images/logo.png");
		logo = new Sprite(new Vector2(0,0), "logo");
		logo.setAlpha(0);
		//done with logo
		
		//walking menu sir
		Textures.get().add("titlesir", "res/images/titlesir.png");
		titleSir = new AnimatedSprite("titlesir", 208, 400, new Vector2(50, 150));
		//
		
		//background
		Textures.get().add("bg", "res/images/menuBG.png");
		background = new Sprite(new Vector2(0,0), "bg");
		
		play = new Button(new Vector2(336, 228), gui, "Play", "button", "buttonDown");//pos, gui, label
		about = new Button(new Vector2(336, 228+65), gui, "About", "button", "buttonDown");
		exit = new Button(new Vector2(336, 228+130), gui, "Exit", "button", "buttonDown");
		
		gui.addButton(play);
		gui.addButton(about);
		gui.addButton(exit);
		started = true;
		
		gameScreen = new GameScreen(this);
	}

	public void update(long deltaTime) {
		this.deltaTime = deltaTime;
		
		while(Mouse.next()) {
			gui.update();
		}
		if(play.clicked()) {
			System.out.println("Going to Game Screen!");
			manager.setScreen(gameScreen);
		}
	
		if(about.clicked())
			manager.setScreen(new AboutScreen(this));

		if(exit.clicked())  {
			System.out.println("Bye bye!");
			manager.getGame().requestClose();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			manager.setScreen(new EditorScreen());
	}

	public void render() {
		//why gui suddenly no render? :O
		if(fadeIn >= 2) {
			background.render(0,0);
			gui.render();
			timer++;
			if(timer >= 10) {
				timer = 0;
				if(fx < 3) {
					fx++;
				}else {
					fx = 0;
				}
			}
			titleSir.render(fx, 0, "right", 0, 0);
		}
		//intro stuffs
		if(logoTimer > 0) {
			if(fadeIn < 2) {
				logo.setAlpha(fadeIn/2);
				fadeIn += deltaTime/1e3;
			}else if (fadeIn >= 2) {//this stuff is extremely messy, but I am very tired, so who cares!
				logo.setAlpha(fadeOut/2);
				fadeOut -= deltaTime/1e3;	
			}
		logoTimer -= deltaTime/1e3;
		logo.render(0, 0);
		}
				//intro stuffs done
	}

	public boolean getStarted() {
		return started;
	}

}
