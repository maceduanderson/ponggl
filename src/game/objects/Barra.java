package game.objects;

import com.jogamp.opengl.GL2;

public class Barra extends GameObject 
{

	public Barra(float x, float y, float altura, float largura)
	{
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.largura = largura;
	}
	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GL2 gl2) 
	{
        gl2.glPushMatrix();
        {
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
        }
		
	}

}
