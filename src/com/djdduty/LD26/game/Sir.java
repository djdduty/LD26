package com.djdduty.LD26.game;

import org.lwjgl.input.Keyboard;

import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.graphic.AnimatedSprite;
import engine.JWolf2D.level.Layer;
import engine.JWolf2D.logic.PlayerBase;

public class Sir extends PlayerBase{
	private float xmove, ymove;
	private AnimatedSprite anim;
	private int timer, row=0, fx;
	private String animState;
	
	public Sir(Vector2 pos, Layer l) {
		super(pos, "sir", l);
		anim = new AnimatedSprite("sir", 28, 54, pos);
		animState = "idle";
	}
	
	public Sir(Vector2 pos, String texName, Layer l) {
		super(pos, texName, l);
		// TODO Auto-generated constructor stub
		anim = new AnimatedSprite("sir", 28, 54, pos);
		animState = "idle";
	}
	
	public void update(long deltaTime) {
		xmove = 0;
		ymove = 0;
		animState = "idle";

		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			ymove = 200 * (deltaTime/1000f);
			animState = "down";
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xmove = -200 * (deltaTime/1000f);
			animState = "left";
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xmove = 200 * (deltaTime/1000f);
			animState = "right";
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			ymove = -200 * (deltaTime/1000f);
			animState = "up";
		}
		
		addVelocity(xmove, ymove);
		checkCollision();
	}

	public void render(int xoff, int yoff) {
		
		if(animState == "idle") {
			anim.render(0, 2, "right", xoff, yoff);
		}
		
		if(animState == "right") {
			timer++;
			if(timer >= 10) {
				timer = 0;
				if(fx < 3) {
					fx++;
				}else {
					fx = 0;
				}
			}
			anim.render(fx, 0, "right", xoff, yoff);
		}
		
		if(animState == "left") {
			timer++;
			if(timer >= 10) {
				timer = 0;
				if(fx < 3) {
					fx++;
				}else {
					fx = 0;
				}
			}
			anim.render(fx, 3, "right", xoff, yoff);
		}
		
		if(animState == "up") {
			timer++;
			if(timer >= 10) {
				timer = 0;
				if(fx < 3) {
					fx++;
				}else {
					fx = 0;
				}
			}
			anim.render(fx, 1, "right", xoff, yoff);
		}
		
		if(animState == "down") {
			timer++;
			if(timer >= 10) {
				timer = 0;
				if(fx < 3) {
					fx++;
				}else {
					fx = 0;
				}
			}
			anim.render(fx, 2, "right", xoff, yoff);
		}
	}
	
	public int getWidth() {
		return 28;
	}
	
	public int getHeight() {
		return 54;
	}
	
	public int getDistanceTravelled() {
		return distance;
	}
}
