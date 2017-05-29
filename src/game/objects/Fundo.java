package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import game.Textura;

public class Fundo extends GameObject
{

	Textura textura;
	public Fundo(float x, float y, float minX, float maxX, float minY, float maxY)
	{
		this.x = x;
		this.y = y;
		this.maxX = maxX;
		this.minX = minX;		
		this.maxY = maxY;
		this.minY = minY;
		//this.textura = new Textura(1);
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GL2 gl2, GLUT glut) {
    	gl2.glPushMatrix();    		
			gl2.glScalef(maxX*2, maxY*2, 0.0f);
			gl2.glColor3f(0.2f, 0, 0);
			gl2.glTranslatef(0, 0, 5.0f);
			//applyTextura(gl2);
			glut.glutSolidCube(1);
//			textura.desabilitarTextura(gl2);			
		gl2.glPopMatrix();
		
	}
	
	public void applyTextura(GL2 gl)
	{
		
	    gl.glMatrixMode(GL2.GL_TEXTURE);
	    gl.glLoadIdentity();                      
	    //gl.glScalef(maxX*2, maxY*2, 0.0f);           
	    gl.glMatrixMode(GL2.GL_MODELVIEW);
		 
		     //habilita o uso de textura
		 //genModo - GL.GL_EYE_LINEAR, GL.GL_OBJECT_LINEAR
		 textura.habilitarTexturaAutomatica(gl, GL2.GL_OBJECT_LINEAR);
		
		 //habilita os filtros
		 textura.setFiltro(GL2.GL_LINEAR);
		 textura.setModo(GL2.GL_MODULATE);
		 textura.setWrap(GL2.GL_REPEAT);        
		 textura.gerarTextura(gl, "texturas/fundo.jpg", 0);
	}
}
