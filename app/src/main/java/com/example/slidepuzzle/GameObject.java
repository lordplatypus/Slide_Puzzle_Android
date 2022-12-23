package com.example.slidepuzzle;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class GameObject
{
    protected int x;
    protected int y;

    protected int id;
    protected final String name;
    protected final String tag;


    public GameObject(int x, int y, int id, String name, String tag)
    {
        this.x = x;
        this.y = y;

        this.id = id;
        this.name = name;
        this.tag = tag;
    }

    protected void update(int deltaTime)
    {}

    protected void draw(Canvas canvas)
    {}

    protected int getX()
    {
        return this.x;
    }

    protected void setX(int x)
    {
        this.x = x;
    }

    protected int getY()
    {
        return this.y;
    }

    protected void setY(int y)
    {
        this.y = y;
    }

    protected int getID()
    {
        return this.id;
    }

    protected void setID(int id)
    {
        this.id = id;
    }

    protected String getName()
    {
        return this.name;
    }

    protected String getTag()
    {
        return this.tag;
    }
}