package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

import game.Textura;

public class Ball extends GameObject
{

	protected  float MAXINCX = 0.3f;
	protected  float MAXINCY = 0.4f;
	protected static final float MAXVAR = 0.09f;
	int raio;
	float incrementoX;
	float incrementoY;
	float variacao;
	Textura textura;
	private float angle = 0;
	
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
		this.textura = new Textura(1);
		
	}
	
	@Override
	public void update() 
	{	
		
		if(this.angle > 360.0f) this.angle = 0;	
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
		
		aplicarTextura(gl2, glut);
		
		gl2.glPushMatrix();
			ligaLuz(gl2);
			iluminacaoEspecular(gl2, glut);
			gl2.glTranslatef(this.x, this.y, 0);
			gl2.glColor3f(1.0f, 1.0f, 0.8f);   //branca
			//glut.glutSolidSphere(1, 15, 15);
			gl2.glRotatef(this.angle += 1.5f, 0, 1.0f, 0);
			solidSphere(1, 15, 15);
			//gl2.glDisable(GL2.GL_LIGHT0);
			textura.desabilitarTexturaAutomatica(gl2);
			
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

    void aplicarTextura(GL2 gl2, GLUT glut)
    {
        int filtro = GL2.GL_LINEAR;
        int wrap = GL2.GL_REPEAT;
        int modo = GL2.GL_MODULATE;
    	gl2.glMatrixMode(GL2.GL_TEXTURE);
    	gl2.glLoadIdentity();
    	gl2.glRotatef(180, 1, 0, 0);
    	gl2.glMatrixMode(GL2.GL_MODELVIEW);
		
		// Desenha um cubo no qual a textura eh aplicada
		//habilita o uso de textura
		textura.habilitarTextura(gl2);
		
		//habilita os filtros
		textura.setFiltro(filtro);
		textura.setModo(GL2.GL_MODULATE);
		textura.setWrap(wrap);
		textura.gerarTextura(gl2, "texturas/mapa.jpg", 0);
    }
    
    private void solidSphere(int raio, int stacks, int columns) 
    {   
        GLU glu = new GLU();   

        GLUquadric quadObj = glu.gluNewQuadric();   
        glu.gluQuadricDrawStyle(quadObj, GLU.GLU_FILL);   
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);   
        glu.gluQuadricTexture(quadObj, true); //Habilita textura  
        glu.gluQuadricNormals(quadObj, GLU.GLU_SMOOTH);
        glu.gluSphere(quadObj, raio, stacks, columns);   
    }

}
