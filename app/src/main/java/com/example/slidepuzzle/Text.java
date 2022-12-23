package com.example.slidepuzzle;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Text extends GameObject
{
    private String text;
    private Paint paint;
    private int offset;

    public Text(String text, int x, int y, Paint paint, int id, String name, String tag)
    {
        super(x, y, id, name, tag);

        this.text = text;
        this.paint = paint;
        this.offset = (int) this.paint.getTextSize();
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawText(text, this.x, this.y + this.offset, this.paint);
    }
}