package com.example.slidepuzzle;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Hint extends GameObject
{
    private Bitmap image;
    private boolean isVisible;

    public Hint(Bitmap image)
    {
        super(0, 0, -1, "Hint", "Hint");
        this.image = image;
        this.isVisible = false;
    }

    @Override
    public void draw(Canvas canvas)
    {
        if (this.isVisible) canvas.drawBitmap(this.image, this.x, this.y, null);
    }

    public void setIsVisible(boolean isVisible)
    {
        this.isVisible = isVisible;
    }

    public boolean getIsVisible()
    {
        return this.isVisible;
    }
}