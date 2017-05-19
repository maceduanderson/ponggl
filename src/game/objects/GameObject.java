package game.objects;

import com.jogamp.opengl.GL2;

public abstract class GameObject
{

    protected float x;
    protected float y;
    protected float altura;
    protected float largura;

    abstract void update();

    public abstract void render(GL2 gl2);
    

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