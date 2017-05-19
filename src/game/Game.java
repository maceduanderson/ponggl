package game;

import java.util.ArrayList;import com.jogamp.opengl.GL2;

import game.objects.GameObject;

public class Game 
{
	
	private ArrayList<GameObject> gameObjects;
	
	
	
	
	public Game(ArrayList<GameObject> gameObjects)
	{
		this.gameObjects = gameObjects;
	}
	
	public void renderAll(GL2 gl2)
	{
		for (GameObject gameObject : this.gameObjects) 
		{
			gameObject.render(gl2);			
		}
	}

}
