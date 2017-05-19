package game.objects;

import com.jogamp.opengl.GL2;

public abstract class GameObject
{

    protected float x;
    protected float y;
    protected float width;
    protected float height;

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

    public float getWidth()
    {
        return width;
    }

    public float getHeight()
    {
        return height;
    }

    public float getCenterY()
    {
        return y + height / 2;
    }
}