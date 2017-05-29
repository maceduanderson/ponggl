package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import game.Game;

public class Barra extends GameObject 
{

	private Ball bola;
	private float VELBARRA;
	
	public Barra(float x, float y,  float minX, float maxX, Ball bola)
	{
		this.x = x;
		this.y = y;
		this.altura = 2;
		this.largura = 4;
		this.maxX = maxX;
		this.minX = minX;
		this.bola = bola;
		this.VELBARRA = 1.5f;
		
	}
	public Barra(float x, float y, float altura, float largura, float maxX, float minX)
	{
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.largura = largura;
		this.maxX = maxX;
		this.minX = minX;
		this.VELBARRA = 1.5f;

	}
	@Override
	public void update() {
		
		if(verificaColisao(bola))
		{
			bola.reverteY();
		}
		
	}

	@Override
	public void render(GL2 gl2, GLUT glut) 
	{
        gl2.glPushMatrix();
        
        	gl2.glTranslatef(this.x, this.y, 0);
        	gl2.glRotatef(0, 0, 0, 0);
        	gl2.glColor3f(0.0f, 0.0f, 1.0f);
        	gl2.glBegin(GL2.GL_QUADS);            
            	gl2.glVertex2f(0, 0);
            	gl2.glVertex2f(0, this.altura);
            	gl2.glVertex2f(this.largura, this.altura);
            	gl2. glVertex2f(this.largura, 0);            
            gl2.glEnd();
            
        gl2.glPopMatrix();
		
	}
	public void moverEsquerda()
	{		
		if(x > minX)
		{			
			x -= VELBARRA;
			System.out.println("Moveu Esquerda : x = " + x);
		}
	}
	public void moverDireita()
	{
		if( x + largura < maxX)
		{			
			System.out.println("Moveu Direita : x = " + x);
			x += VELBARRA;
		}
	}
	

}
