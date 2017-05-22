package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Fundo extends GameObject
{

	public Fundo(float x, float y, float minX, float maxX, float minY, float maxY)
	{
		this.x = x;
		this.y = y;
		this.maxX = maxX;
		this.minX = minX;		
		this.maxY = maxY;
		this.minY = minY;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GL2 gl2, GLUT glut) {
    	gl2.glPushMatrix();
    		
			gl2.glScalef(maxX*2, maxY*2, 0.0f);
			gl2.glColor3f(0, 0, 0);
			gl2.glTranslatef(0, 0, -0.5f);
			glut.glutSolidCube(1);
		gl2.glPopMatrix();
		
	}
	

}
