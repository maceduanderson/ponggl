package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class MessageBox extends GameObject
{
	private String [] frases;
	private boolean onOff;

	public MessageBox(float minX, float maxX, float minY, float maxY)
	{
		this.maxX = maxX;
		this.minX = minX;		
		this.maxY = maxY;
		this.minY = minY;		
		this.x = 0;
		this.y = maxY - 2;
		this.onOff = false;
	}

	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	@Override
	public void render(GL2 gl2, GLUT glut) 
	{
		float yPos = this.y;
		if(onOff)
		{			
			for (String frase : frases) 
			{
				
				gl2.glPushMatrix();
					gl2.glColor3f(1,1,1);
					gl2.glRasterPos3f(this.x, yPos, 1.0f);		      
					glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, frase);
					yPos -= 1;
				gl2.glPopMatrix();
			}
		}
		
	}
	
	public void setFrase(String[] frases)
	{
		this.frases = frases;
	}
	
	public void setOnOff(boolean onOff)
	{
		this.onOff = onOff;
	}
	
	

}
