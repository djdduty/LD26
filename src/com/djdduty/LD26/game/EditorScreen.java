package com.djdduty.LD26.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import engine.JWolf2D.core.Screen;
import engine.JWolf2D.core.ScreenManager;
import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.level.Layer;
import engine.JWolf2D.level.Level;
import engine.JWolf2D.logic.GameObject;

public class EditorScreen implements Screen{
	private GameObject preview;
	private Level level;
	private Layer layer, carpetLayer;
	private boolean started=false, teaSelected;
	private ScreenManager manager;
	private String texName;
	private int xoff, yoff;
	
	public void init(ScreenManager manager) {
		this.manager = manager;
		
		//init level
		level = new Level("level");
		layer = new Layer("main", level);
		carpetLayer = new Layer("carpet", level);
		level.addLayer(layer);
		level.addLayer(carpetLayer);
		level.setMainLayer(layer);
		
		//init preview object
		preview = new GameObject(new Vector2(Mouse.getX(), Mouse.getY()), "bookshelfDown");
		xoff = 0;
		yoff = 0;
		started = true;
	}

	public void update(long deltaTime) {
		//update preview
		int posX = Mouse.getX();
		int posY = (int) ((Mouse.getY()*-1)+Display.getHeight());
		int posX2 = (int) ((posX-=posX%preview.getSize().x) - Math.round(xoff-xoff%64));
		int posY2 = (int) ((int) ((posY-=posY%preview.getSize().y)) - Math.round(yoff-yoff%64));
		preview.setPos(new Vector2(posX2, posY2));
		
		//handle keys
		while(Keyboard.next()) {
			if(Keyboard.isKeyDown(Keyboard.KEY_1))
				preview.setTexture("bookshelfDown");
			
			if(Keyboard.isKeyDown(Keyboard.KEY_2))
				preview.setTexture("bookshelfUp");
			
			if(Keyboard.isKeyDown(Keyboard.KEY_3))
				preview.setTexture("bookshelfLeft");
			
			if(Keyboard.isKeyDown(Keyboard.KEY_4)) 
				preview.setTexture("bookshelfRight");
			
			if(Keyboard.isKeyDown(Keyboard.KEY_5))
				preview.setTexture("bookshelfRightUp");
			
			if(Keyboard.isKeyDown(Keyboard.KEY_6)) 
				preview.setTexture("bookshelfLeftUp");
			
			if(Keyboard.isKeyDown(Keyboard.KEY_7)) 
				preview.setTexture("bookshelfR");
			
			if(Keyboard.isKeyDown(Keyboard.KEY_8)) 
				preview.setTexture("bookshelfL");
			
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) 
				xoff -= 20;
			
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)) 
				xoff += 20;
			
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)) 
				yoff += 20;
			
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) 
				yoff -= 20;
			
			if(Keyboard.isKeyDown(Keyboard.KEY_S)) 
				level.save();//why no work... =/
			
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				layer.addObject(new GameObject(preview.getPos(), "nulltex"));
			}
			
			if(Keyboard.isKeyDown(Keyboard.KEY_D))
				System.out.println(preview.getPos().x + ", " + preview.getPos().y);//level.getMainLayer().removeObject(level.containsPoint(level.getMainLayer().getName(), new Vector2(posX + xoff, posY + yoff)));
			
			if(Keyboard.isKeyDown(Keyboard.KEY_L))
				level.load("src/res/levels/level3.xml");
		}
		
		while(Mouse.next()) {
			if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
				layer.addObject(new GameObject(preview.getPos(), preview.getTexture().getTextureName()));
			}
			
			if(Mouse.getEventButton() == 1 && Mouse.getEventButtonState()) {
				carpetLayer.addObject(new GameObject(preview.getPos(), "floor"));
			}
			
			if(Mouse.getEventButton() == 2 && Mouse.getEventButtonState()) {
				level.tea = new Tea(preview.getPos());
			}
		}
		preview.update(deltaTime);
		level.update(deltaTime);
	}

	public void render() {
		level.render(xoff, yoff);
		preview.render(xoff, yoff);
	}

	public boolean getStarted() {
		return started;
	}

}
