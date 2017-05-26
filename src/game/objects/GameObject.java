package game.objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public abstract class GameObject
{

	protected float maxX,minX, maxY, minY;
    protected float x;
    protected float y;
    protected float altura;
    protected float largura;
    

    public abstract void update();

    public abstract void render(GL2 gl2, GLUT glut);
    
    
 
    
    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getaltura()
    {
        return altura;
    }

    public float getlargura()
    {
        return largura;
    }

    public float getCenterY()
    {
        return y + largura / 2;
    }
    
	public float clamp(float val, float min, float max)
	{
		return Math.max(min, Math.min(val, max));
	}
	
	public boolean verificaColisao(Ball bola)
	{
		float distX, distY, dist;
		float proxX, proxY;
		
		proxX = clamp(bola.getX(), x, x + largura);
		proxY = clamp(bola.getY(), y, y + altura);
				
		distX = bola.getX() - proxX;
		distY = bola.getY() - proxY;
		
		dist = (float) (Math.pow(distX, 2) + Math.pow(distY, 2));
		
		
		return Math.abs(dist) < Math.pow(bola.raio, 2);
		
	}
	
    public void ligaLuz(GL2 gl2) 
    {
    	
    	int TONALIZACAO = GL2.GL_SMOOTH;
    	// habilita a definição da cor do material a partir da cor corrente
    	gl2.glEnable(GL2.GL_COLOR_MATERIAL);
    
    	// habilita o uso da iluminação na cena
    	gl2.glEnable(GL2.GL_LIGHTING);
    	// habilita a luz de número 0
    	gl2.glEnable(GL2.GL_LIGHT0);
   	/*
    	 * Especifica o Modelo de tonalização a ser utilizado GL_FLAT -> modelo
    	 * de tonalização flat GL_SMOOTH -> modelo de tonalização GOURAUD
    	 * (default)
    	 */
    	gl2.glShadeModel(TONALIZACAO);
    }


}