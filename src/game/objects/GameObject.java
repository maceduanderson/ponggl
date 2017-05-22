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
}