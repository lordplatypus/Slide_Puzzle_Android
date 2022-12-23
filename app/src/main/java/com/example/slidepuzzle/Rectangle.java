package com.example.slidepuzzle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Rectangle extends GameObject
{
    private final Rect rectangle;
    private int width;
    private int height;
    private int offset; //stroke width / 2
    private final Paint paint;

    public Rectangle(int x, int y, int width, int height, Paint paint, int id, String name, String tag)
    {
        super(x, y, id, name, tag);
        this.width = width;
        this.height = height;
        this.paint = paint;

        this.offset = (int) this.paint.getStrokeWidth() / 2;
        this.rectangle = new Rect(this.x + this.offset, this.y + this.offset, this.x + this.width - this.offset, this.y + this.height - this.offset);
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawRect(this.rectangle, this.paint);
    }

    @Override
    public void setX(int x)
    {
        this.x = x + this.offset;
        this.rectangle.left = this.x;
        this.rectangle.right = x + this.width - this.offset;
    }

    @Override
    public void setY(int y)
    {
        this.y = y + this.offset;
        this.rectangle.top = y;
        this.rectangle.bottom = y + this.height - this.offset;
    }
}