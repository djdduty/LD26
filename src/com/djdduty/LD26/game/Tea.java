package com.djdduty.LD26.game;

import engine.JWolf2D.geom.Vector2;
import engine.JWolf2D.logic.GameObject;

public class Tea extends GameObject{

	public Tea(Vector2 pos) {
		this(pos, "tea");
	}
	
	public Tea(Vector2 pos, String texName) {
		super(pos, texName);
		
	}

}
