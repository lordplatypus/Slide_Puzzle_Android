package com.example.slidepuzzle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback
{
    private GameThread gameThread;
    private final GameObjectManager gom;
    private Input input;
    private final Options options;

    public GameSurface(Context context, Options options)
    {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
        this.gom = new GameObjectManager();
        this.options = options;
    }

    public void update(int deltaTime)
    {
        this.gom.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        this.gom.draw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        Bitmap image;
        image = BitmapFactory.decodeFile(this.options.getImagePath());
        this.gom.addGameObject(new GameManager(this, image, this.options.getRows(), this.options.getColumns(), -1, "GameManager", "GM"));
        this.input = new Input(this);

        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while(retry)
        {
            try
            {
                this.gameThread.setRunning(false);
                this.gameThread.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            retry = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        //Log.d("TEST", "onTouch: " + event.toString());
        if (input.getDetector().onTouchEvent(event))
        {
            return true;
        }
        return super.onTouchEvent(event);
    }

    public GameObjectManager getGameObjectManager()
    {
        return this.gom;
    }

    public Options getOptions()
    {
        return this.options;
    }
}