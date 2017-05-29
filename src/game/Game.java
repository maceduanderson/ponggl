package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicTextAreaUI;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import game.objects.Arena;
import game.objects.Ball;
import game.objects.Barra;
import game.objects.BarraDaTreta;
import game.objects.Fundo;
import game.objects.GameObject;
import game.objects.MessageBox;

public class Game 
{
	
	private ArrayList<GameObject> gameObjects;
	
	private int vidas;
	private int pontos;
	private int fase;
	private  float maxX,minX, maxY, minY;
	
	
	private Textura textura;
	private MessageBox message;
	private Barra barra;
	private Ball bola;
	private Fundo fundo;
	private Arena arena;
	private BarraDaTreta barraTreta;	
    public enum state
    {   
    	INIT,
    	STARTED,
    	RESET,
    	PAUSED, 
    	STOP    	
    }    
    private float angle;
	private state estado;
	
	
	private String[] INITSTRING = {"PONGGL", "1 : INICIA", "2 : PAUSA", "3 : PARA"};
	private String[] STARTEDSTRING;
	private String[] PAUSESTRING;
	private String[] STOPSTRING;
	
	public Game(ArrayList<GameObject> gameObjects)
	{
		this.gameObjects = gameObjects;
	}
	public Game(float minX, float maxX, float minY, float maxY)
	{
		
		this.vidas = 5;
		this.fase = 0;
		this.pontos = 0;
		
		this.maxX = maxX;
		this.minX = minX;		
		this.maxY = maxY;
		this.minY = minY;
		
		this.gameObjects = new ArrayList<>();
		this.angle = 0;
		this.estado = state.INIT;
		
		this.bola = new Ball(0, 0, minX,maxX, minY, maxY, 1);
		this.barra = new Barra(0, -8, minX , maxX, this.bola);
		this.barraTreta = new BarraDaTreta(0, 5, minX, maxX, bola);
		this.fundo  =new Fundo(0, 0, minX, maxX, minY, maxY);
		this.arena = new Arena(minX, maxX, minY, maxY, this.bola);
		this.message = new MessageBox(minX, maxX, minY, maxY);
		
		
		this.gameObjects.add(barra);
		this.gameObjects.add(bola);
		this.gameObjects.add(barraTreta);
		this.gameObjects.add(fundo);
		this.gameObjects.add(arena);
		this.gameObjects.add(message);
		
	}
	
	void reset()
	{
		this.gameObjects = new ArrayList<>();
		this.angle = 0;
		
		
		this.bola = new Ball(0, 0, minX,maxX, minY, maxY, 1);
		this.barra = new Barra(0, -8, minX , maxX, this.bola);
		this.barraTreta = new BarraDaTreta(0, 5, minX, maxX, bola);
		this.fundo  =new Fundo(0, 0, minX, maxX, minY, maxY);
		this.arena = new Arena(minX, maxX, minY, maxY, this.bola);
		this.message = new MessageBox(minX, maxX, minY, maxY);
		
		
		this.gameObjects.add(barra);
		this.gameObjects.add(bola);
		this.gameObjects.add(barraTreta);
		this.gameObjects.add(fundo);
		this.gameObjects.add(arena);
		this.gameObjects.add(message);
		
	}
	
	public void renderAll(GL2 gl2, GLUT glut)
	{
		
		
		for (GameObject gameObject : this.gameObjects) 
		{
			switch(estado) 
			{
				case INIT:
					gl2.glRotatef(this.angle += 0.1f, 0, 1.0f, 0);
					if(this.angle > 360.0f) this.angle = 0;					
					message.setFrase(INITSTRING);
					message.setOnOff(true);					
					break;
				case STARTED:
					gl2.glRotatef(this.angle, 0, 0, 0);					
					gameObject.update();				
					tratarEventos();
					STARTEDSTRING = new String[] {"PONTOS : " + pontos , "VIDAS : " + vidas};
					message.setFrase(STARTEDSTRING);					
					message.setOnOff(true);
					break;
				case PAUSED:
					PAUSESTRING  = new String[]{"1 : RETOMAR"};
					message.setFrase(PAUSESTRING);
					message.setOnOff(true);
					break;
				case STOP:
					reset();
					vidas = 5;
					pontos = 0;
					STOPSTRING = new String[]{"VOCE PERDEU!", "1 : RETENTAR"};
					message.setFrase(STOPSTRING);
					message.setOnOff(true);
					break;
				case RESET:
					reset();
					this.estado = state.PAUSED;
					break;
				default:
					break;
			}
			
			
			gameObject.render(gl2, glut);			
		}
	}
	
	
	
	public void tratarEventos()
	{		
		// bola bateu na barra
		if( barra.verificaColisao(bola) ) 
		{
			pontos += 1;
		}
		
		if(pontos > 200)
		{
			barraTreta.setLiga(true);
			bola.duplicaVelocidade();
		}
						
		// bola bateu no fundo
		float bolaXPos =  bola.getY() - bola.getRaio();
		if(bolaXPos <= minY)
		{
			estado = state.RESET;
			vidas--;
		}
		if(vidas <= 0)
		{
			estado = state.STOP;
		}
		
		
		
	}
	
	public void keyboardEvent(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		
		
		if(estado == state.INIT)
		{
			// tratar INICIAR;
		}
		
		
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
			case KeyEvent.VK_1:
				this.angle = 0;
				this.estado = state.STARTED;
				break;
			case KeyEvent.VK_2:
				this.angle = 0;
				this.estado = state.PAUSED;
				break;				
			case KeyEvent.VK_3:
				this.angle = 0;
				this.estado = state.STOP;
			break;

			

		}	
	}
	


}
