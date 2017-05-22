package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class Barra extends GameObject 
{

	
	
	public Barra(float x, float y,  float minX, float maxX)
	{
		this.x = x;
		this.y = y;
		this.altura = 2;
		this.largura = 4;
		this.maxX = maxX;
		this.minX = minX;
		
	}
	public Barra(float x, float y, float altura, float largura, float maxX, float minX)
	{
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.largura = largura;
		this.maxX = maxX;
		this.minX = minX;

	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GL2 gl2, GLUT glut) 
	{
        gl2.glPushMatrix();
        
        	gl2.glTranslatef(this.x, this.y, 0);
        	gl2.glRotatef(0, 0, 0, 0);

        	gl2.glBegin(GL2.GL_QUADS);
            {
            	gl2.glVertex2f(0, 0);
            	gl2.glVertex2f(0, this.altura);
            	gl2.glVertex2f(this.largura, this.altura);
            	gl2. glVertex2f(this.largura, 0);
            }
            gl2.glEnd();
        gl2.glPopMatrix();
		
	}
	public void moverEsquerda()
	{		
		if(x > minX)
		{			
			x -= 1;
			System.out.println("Moveu Esquerda : x = " + x);
		}
	}
	public void moverDireita()
	{
		if( x + largura < maxX)
		{			
			System.out.println("Moveu Direita : x = " + x);
			x += 1;
		}
	}


}
