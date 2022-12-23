package com.example.slidepuzzle;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.core.view.GestureDetectorCompat;
import java.lang.Math;

public class Input implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener
{
    private final GestureDetectorCompat detector;
    private final GameSurface game;
    private final GameManager gm;

    public Input(GameSurface gameSurface)
    {
        this.game = gameSurface;
        this.detector = new GestureDetectorCompat(this.game.getContext(), this);
        this.detector.setOnDoubleTapListener(this);
        this.gm = (GameManager) this.game.getGameObjectManager().searchByName("GameManager");
    }

    public GestureDetectorCompat getDetector()
    {
        return this.detector;
    }

    @Override
    public boolean onDown(MotionEvent event)
    {
        //Log.d("TEST", "onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY)
    {
        //Log.d("TEST", "onFling: " + event1.toString());
        //Log.d("TEST", "onFling: " + event2.toString());
        int direction;
        if (Math.abs(event1.getX() - event2.getX()) > Math.abs(event1.getY() - event2.getY()))
        {
            if (event1.getX() < event2.getX()) direction = 0;
            else direction = 1;
        }
        else
        {
            if (event1.getY() > event2.getY()) direction = 2;
            else direction = 3;
        }

        gm.swap(direction);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event)
    {
        this.gm.setHintVisibility();
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY)
    {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event)
    {}

    @Override
    public boolean onSingleTapUp(MotionEvent event)
    {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        return true;
    }

}