package com.example.slidepuzzle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Box extends GameObject
{
    private final GameSurface game;
    private Bitmap image;
    private int width;
    private int height;
    private int correctLocationID;
    private boolean isVisible;
    private int targetX;
    private int targetY;

    private Rectangle outline;
    private Text numberText;

    public Box(GameSurface gameSurface, Bitmap image, int width, int height, int x, int y, int id, String name, String tag)
    {
        super(x, y, id, name, tag);

        this.game = gameSurface;
        this.image = image;
        this.width = width;
        this.height = height;
        this.correctLocationID = id;
        this.isVisible = true;
        this.targetX = x;
        this.targetY = y;

        Paint outlinePaint = new Paint();
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setColor(game.getOptions().getOutlineColor());
        outlinePaint.setStrokeWidth(this.game.getOptions().getOutlineSize());
        this.outline = new Rectangle(this.x, this.y, this.width, this.height, outlinePaint, this.id, "Outline", "Rectangle");

        Paint numberPaint = new Paint();
        numberPaint.setTextSize(this.game.getOptions().getNumberSize());
        numberPaint.setColor(this.game.getOptions().getNumberColor());
        this.numberText = new Text(Integer.toString(this.correctLocationID), this.x, this.y, numberPaint, this.id, "NumberText", "Text");
    }

    @Override
    public void update(int deltaTime)
    {
        if (this.x != this.targetX)
        {
            this.x = lerp((double) deltaTime / 100.0, this.x, this.targetX);
            this.outline.setX(this.x);
            this.numberText.setX(this.x);
        }
        if (this.y != this.targetY)
        {
            this.y = lerp((double) deltaTime / 100.0, this.y, this.targetY);
            this.outline.setY(this.y);
            this.numberText.setY(this.y);
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        if (!isVisible) return;
        canvas.drawBitmap(image, x, y, null);
        if (game.getOptions().getAllowOutline() && inRightPlace()) this.outline.draw(canvas);
        if (game.getOptions().getAllowNumber()) this.numberText.draw(canvas);
    }

    @Override
    public int getX()
    {
        return this.targetX;
    }

    @Override
    public void setX(int x)
    {
        this.targetX = x;
    }

    @Override
    public int getY()
    {
        return this.targetY;
    }

    @Override
    public void setY(int y)
    {
        this.targetY = y;
    }

    public int getCorrectLocationID()
    {
        return this.correctLocationID;
    }

    public boolean inRightPlace()
    {
        if(this.id == this.correctLocationID) return true;
        return false;
    }

    public void setVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    private int lerp(double time, int pos, int targetPos)
    {
        if (Math.abs(pos - targetPos) < 8) return targetPos;
        return (int) ((1.0 - time) * (double)pos + time * (double)targetPos);
    }
}