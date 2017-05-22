package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class Ball extends GameObject
{

	protected static final float MAXINCX = 0.5f;
	protected static final float MAXINCY = 0.6f;
	protected static final float MAXVAR = 0.09f;
	int raio;
	float incrementoX;
	float incrementoY;
	float variacao;
	
	public Ball(float x, float y, float minX, float maxX, float minY, float maxY, int raio)
	{
		this.x = x;
		this.y = y;
		this.maxX = maxX;
		this.minX = minX;
		this.raio = raio;
		this.maxY = maxY;
		this.minY = minY;
		this.incrementoX = MAXINCX;
		this.incrementoY = MAXINCY;
		this.variacao = MAXVAR;
		
	}
	
	@Override
	public void update() 
	{		
		if( (x+raio) > maxX) reverteX();
		if( (x+raio) < minX) reverteX();
		
		if( (y+raio) > maxY) reverteY();
		if( (y+raio) < minY) reverteY();
		
		x += incrementoX;
		y += incrementoY;
	}
	
	void reverteX()
	{
		incrementoX = -incrementoX;
	}
	void reverteY()
	{
		incrementoY = -incrementoY;// * variacao;
		//if(incrementoY > MAXINCY) incrementoY = MAXINCY;
		//if(incrementoY < -MAXINCY) incrementoY = -MAXINCY;
	}

	@Override
	public void render(GL2 gl2, GLUT glut) 
	{	
		gl2.glPushMatrix();
			gl2.glTranslatef(this.x, this.y, 0);
			glut.glutSolidSphere(1, 15, 15);
		gl2.glPopMatrix();
	}


}
