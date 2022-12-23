package com.example.slidepuzzle;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread
{
    private boolean isRunning;
    private final GameSurface gameSurface;
    private final SurfaceHolder surfaceHolder;
    private long now;
    private long past;

    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder)
    {
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
        this.now = System.nanoTime();
        this.past = this.now;
    }

    @Override
    public void run()
    {
        long startTime = System.nanoTime();

        while(isRunning)
        {
            Canvas canvas = null;
            try
            {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(canvas)
                {
                    this.gameSurface.update(deltaTime());
                    this.gameSurface.draw(canvas);
                }
            }
            catch(Exception e)
            {
                //nothing
            }
            finally
            {
                if(canvas != null)
                {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime();
            long waitTime = (now - startTime)/1000000;
            if(waitTime < 10)
            {
                waitTime = 10;
            }
            System.out.print("Wait time = " + waitTime);

            try
            {
                this.sleep(waitTime);
            }
            catch(InterruptedException e)
            {
                //nothing
            }
            startTime = System.nanoTime();
            System.out.print(".");
        }
    }

    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    private int deltaTime()
    {
        this.now = System.nanoTime();
        int deltaTime = (int) ((this.now - this.past) / 1000000);
        this.past = this.now;
        //Log.v("delta time", Integer.toString(deltaTime));
        return deltaTime;
    }
}