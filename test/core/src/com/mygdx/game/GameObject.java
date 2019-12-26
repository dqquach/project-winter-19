package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

//Generic game object
public abstract class GameObject {
	public abstract int collisionWith(Rectangle r);
	public abstract void action(int type, float x, float y);
	public abstract void update(float delta);
	public abstract void setPosition(float x, float y);
	public abstract void moveLeft(float delta);
	public abstract void moveRight(float delta);
	public abstract void draw(SpriteBatch batch);
	public abstract void jump();
	public abstract Rectangle getHitbox();
	
	

}
