package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	OrthographicCamera camera;
    SpriteBatch batch;
    MyPlayer player;
    Sprite sprite;
    ArrayList<GameObject> listOfObjects = new ArrayList<GameObject>();
    
    @Override
    public void create () {
    	camera = new OrthographicCamera();
    	camera.setToOrtho(false, 1920, 1080);
        batch = new SpriteBatch();
        
        player = new MyPlayer();
        player.setPosition(400, 400);
        
        listOfObjects.add(new Walls(0,0));
        listOfObjects.add(new Walls(1910,0));
        listOfObjects.add(new GroundOrCeiling(0,0));
        listOfObjects.add(new GroundOrCeiling(0,1070));
        listOfObjects.add(new Block(0,0));
        listOfObjects.add(new Block(64,0));
        listOfObjects.add(new Block(128,0));
        listOfObjects.add(new Block(192,0));
        listOfObjects.add(new Pillar(192,64));
        listOfObjects.add(new Block(256,0));
        listOfObjects.add(new Block(320,0));
        listOfObjects.add(new Block(384,0));
        listOfObjects.add(new Block(448,0));
        listOfObjects.add(new Block(512,64));
        listOfObjects.add(new Block(576,128));
        listOfObjects.add(new Block(640,192));
        listOfObjects.add(new Block(704,256));
        
    }

    @Override
    public void render () {
    	//Render Background
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //Draw objects
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        for (GameObject t : listOfObjects)
        {
        	t.draw(batch);
        }
        batch.end();
        
        //******************Updates******************//
        
        
        /*Constantly update character position
         * if cursor is not pressed, update the position w.r.t gravity
         */
        if (!Gdx.input.isTouched())
        player.update(Gdx.graphics.getDeltaTime());
        
        
        //Create ground, 800 = width of the entire screen, 0 = height from the bottom
        Rectangle ground = new Rectangle(0,0,800,10);
        if (player.collisionWith(ground) != -1)
        {
        	player.action(1, 0, 10);
        }
        
        //If the player collides with any object, the player's y position changes to be on top of the object
        for (GameObject t: listOfObjects)
        {
        	switch (player.collisionWith(t.getHitbox()))
        	{
        	
        	case 1:
        		player.action(1, 0,t.getHitbox().y + t.getHitbox().height);
        		break;
        	case 2:
        		player.action(2, t.getHitbox().x + t.getHitbox().width - 2 , 0);
        		break;
        	case 3:
        		player.action(3, t.getHitbox().x - player.getHitbox().width - 2, 0);
        		break;
        	case 4:
        		player.action(4, 0, t.getHitbox().y-player.getHitbox().height);
        		break;
        	}
        	
        }
        
        
        //*****************Controls******************//
        
        //Cursor Press
        if (Gdx.input.isTouched())
        {
        	player.accelerateToPoint(Gdx.graphics.getDeltaTime(), Gdx.input.getX(), Gdx.input.getY());
        }
        //Move Left
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        	player.moveLeft(Gdx.graphics.getDeltaTime());
        
        //Move Right
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        	player.moveRight(Gdx.graphics.getDeltaTime());
        
        //Jump
        if (Gdx.input.isKeyPressed(Input.Keys.W))
        	player.jump();
        
    }
    
    @Override
    public void dispose () {
        //batch.dispose();
        //img.dispose();
    }
}