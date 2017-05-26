package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Arena extends GameObject 
{
	private Ball bola;

	public Arena(float minX, float maxX, float minY, float maxY, Ball bola)
	{
		this.maxX = maxX;
		this.minX = minX;		
		this.maxY = maxY;
		this.minY = minY;
		this.bola = bola;
		this.x = minX;
		this.y = minY;
		this.altura = maxY;
		this.largura = maxX;
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GL2 gl2, GLUT glut) 
	{
		gl2.glPushMatrix();
			gl2.glColor3f(1.0f, 1.0f, 1.0f);
			gl2.glTranslatef(this.minX, this.minY, 0);
	    	gl2.glBegin(GL2.GL_LINES);            
		    	gl2.glVertex2f(0, 0);
		    	gl2.glVertex2f(0, this.maxY * 2);
		    	gl2.glVertex2f(this.maxY * 2, this.maxX * 2);
		    	gl2. glVertex2f(this.maxY * 2, 0);            
	    	gl2.glEnd();
	    gl2.glPopMatrix();
				
	}
	

}
