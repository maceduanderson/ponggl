package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class BarraDaTreta extends GameObject
{
	private Ball bola;
	private float VELBARRA;
	
	private boolean liga;
	private boolean direcao;  
	
	public BarraDaTreta(float x, float y,  float minX, float maxX, Ball bola)
	{
		this.x = x;
		this.y = y;
		this.altura = 1;
		this.largura = 2;
		this.maxX = maxX;
		this.minX = minX;
		this.bola = bola;
		this.VELBARRA = 0.5f;
		this.direcao = true; // direira;
		this.liga = false;
		
	}
	@Override
	public void update() {
		
		if(liga)
		{
			if(verificaColisao(bola))
			{
				bola.reverteY();
			}
			
			if(direcao)
			{
				if( x + largura < maxX)
				{
					moverDireita();
				}else direcao  = false;
					
			}else
			{
				if(x > minX)
				{
					moverEsquerda();
				}else direcao = true;
			}
		}
		
	}

	@Override
	public void render(GL2 gl2, GLUT glut) 
	{
		if(liga)
		{
	        gl2.glPushMatrix();        
	        	gl2.glTranslatef(this.x, this.y, 0);
	        	gl2.glRotatef(0, 0, 0, 0);
	        	gl2.glColor3f(1.0f, 0.0f, 1.0f);
	        	gl2.glBegin(GL2.GL_QUADS);            
	            	gl2.glVertex2f(0, 0);
	            	gl2.glVertex2f(0, this.altura);
	            	gl2.glVertex2f(this.largura, this.altura);
	            	gl2. glVertex2f(this.largura, 0);            
	            gl2.glEnd();
	            
	        gl2.glPopMatrix();
		}
		
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
	
	public void setLiga(boolean liga)
	{
		this.liga = liga;
	}
	


}
