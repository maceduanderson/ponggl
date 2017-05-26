package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class Ball extends GameObject
{

	protected  float MAXINCX = 0.3f;
	protected  float MAXINCY = 0.4f;
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
			ligaLuz(gl2);
			iluminacaoEspecular(gl2, glut);
			gl2.glTranslatef(this.x, this.y, 0);
			gl2.glColor3f(1.0f, 1.0f, 0.8f);   //branca
			glut.glutSolidSphere(1, 15, 15);
			//gl2.glDisable(GL2.GL_LIGHT0);
			
		gl2.glPopMatrix();
	}
	
    public void iluminacaoEspecular(GL2 gl2, GLUT glut)
    {
        float luzAmbiente[] = {1.0f, 1.0f, 0.0f, 1.0f};
        float luzEspecular[]= {1.0f, 1.0f, 0.8f, 1.0f};
        float luzDifusa[]= {1.0f, 1.0f, 0.8f, 1.0f};
        float posicaoLuz[]={this.x, this.y, 0.0f, 1.0f};
        
        
        //capacidade de brilho do material
    	float especularidade[]={1.0f, 1.0f, 0.0f, 1.0f};
    	//define a reflectÃ¢ncia do material
    	gl2.glMaterialfv(  GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, especularidade, 0);

    	//gl2.glLightModelfv(GL2.GL_LIGHT_MODEL_AMBIENT, luzAmbiente, 0);
    	//define a concentraÂ�â€¹o do brilho
    	gl2.glMateriali(GL2.GL_FRONT, GL2.GL_SHININESS, 10);

        //define os parÃ¢metros de luz de nÃºmero 0 (zero)
    	gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, luzDifusa, 0);
    	gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, luzAmbiente, 0);
    	gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, luzEspecular, 0);
    	gl2.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, posicaoLuz, 0);
    }
    
    
    public int getRaio()
    {
    	return raio;
    }
    
    public void duplicaVelocidade()
    {
    	MAXINCX = 0.6f;
    	MAXINCY = 0.8f;
    }


}
