package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import game.objects.Ball;
import game.objects.Barra;
import game.objects.Fundo;
import game.objects.GameObject;

public class Game 
{
	
	private ArrayList<GameObject> gameObjects;
	
	private Barra barra;
	private Ball bola;
	private Fundo fundo;
	
	
	
	public Game(ArrayList<GameObject> gameObjects)
	{
		this.gameObjects = gameObjects;
	}
	public Game(float minX, float maxX, float minY, float maxY)
	{
		this.gameObjects = new ArrayList<>();
		
		
		this.bola = new Ball(0, 0, minX,maxX, minY, maxY, 1);
		this.barra = new Barra(0, -8, minX , maxX, this.bola);
		this.fundo  =new Fundo(0, 0, minX, maxX, minY, maxY);
		
		this.gameObjects.add(barra);
		this.gameObjects.add(bola);
		this.gameObjects.add(fundo);
	}
	
	public void renderAll(GL2 gl2, GLUT glut)
	{
		for (GameObject gameObject : this.gameObjects) 
		{
			gameObject.update();
			gameObject.render(gl2, glut);			
		}
	}
	
	public void keyboardEvent(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		switch(keyCode)
		{
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_LEFT:
				barra.moverEsquerda();
				break;		
			case KeyEvent.VK_RIGHT:			
				barra.moverDireita();
				break;
		}	
	}
	


}
