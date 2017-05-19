package game.objects;

import com.jogamp.opengl.GL2;

public class Ball extends GameObject
{

	
	@Override
	void update() {
		
		
	}

	@Override
	public void render(GL2 gl2) 
	{
		
		gl2.glBegin(GL2.GL_QUADS);
			gl2.glVertex2f(0, 0);
			gl2.glVertex2f(this.x, 0);
			gl2.glVertex2f(5, -8);
			gl2.glVertex2f(5,-9);
		gl2.glEnd();	
		
	}
	

}
