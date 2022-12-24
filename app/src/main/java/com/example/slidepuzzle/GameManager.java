package com.example.slidepuzzle;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Random;

public class GameManager extends GameObject
{
    private final GameSurface game;
    private Bitmap image;
    private int rows;
    private int columns;
    private Box emptyBox;
    private boolean showHint;
    private Hint hint;

    public GameManager(GameSurface gameSurface, Bitmap image, int rows, int columns, int id, String name, String tag)
    {
        super(0, 0, id, name, tag);

        this.game = gameSurface;
        this.image = image;
        this.rows = rows;
        this.columns = columns;
        this.showHint = false;

        resizeImage();
        CreateBoxes();
        randomizeBoxes();
        if (this.game.getOptions().getAllowHint()) addHint();
    }

    private void resizeImage()
    {
        if (this.image.getWidth() > this.game.getWidth() || this.image.getHeight() > this.game.getHeight())
        {
            if (this.image.getWidth() - this.game.getWidth() > this.image.getHeight() - this.game.getHeight())
            {
                float w = this.game.getWidth();
                float h = this.image.getHeight() / (this.image.getWidth() / w);
                this.image = Bitmap.createScaledBitmap(this.image, (int) w, (int) h, true);
            }
            else
            {
                float h = this.game.getHeight();
                float w = this.image.getWidth() / (this.image.getHeight() / h);
                this.image = Bitmap.createScaledBitmap(image, (int) w, (int) h, true);
            }
        }
        else
        {
            float w = this.game.getWidth();
            float h = image.getHeight() + (w - this.image.getWidth());
            this.image = Bitmap.createScaledBitmap(image, (int) w, (int) h, true);
        }

        Log.v("Image width", Integer.toString(this.image.getWidth()));
        Log.v("Image height", Integer.toString(this.image.getHeight()));
    }

    private void CreateBoxes()
    {
        int boxWidth = this.image.getWidth() / this.columns;
        int boxHeight = this.image.getHeight() / this.rows;
        int x = 0;
        int y = 0;
        int emptyBoxID = this.rows * this.columns - 1;
        if (this.game.getOptions().getRandomStart())
        {
            Random rand = new Random();
            emptyBoxID = rand.nextInt(this.rows * this.columns - 1);
        }

        for (int i = 0; i < this.rows * this.columns; i++)
        {
            Bitmap boxBM = Bitmap.createBitmap(this.image, x, y, boxWidth, boxHeight);
            if (i == emptyBoxID)
            {
                this.emptyBox = new Box(this.game, boxBM, boxWidth, boxHeight, x, y, i, "Box", "Empty");
                this.game.getGameObjectManager().addGameObject(this.emptyBox);
                this.emptyBox.setVisible(false);
            }
            else this.game.getGameObjectManager().addGameObject(new Box(this.game, boxBM, boxWidth, boxHeight, x, y, i, "Box", "Box"));
            x += boxWidth;
            if (x >= image.getWidth())
            {
                x = 0;
                y += boxHeight;
            }
        }
    }

    private void randomizeBoxes()
    {
        Random rand = new Random();

        for (int i = 0; i < 100 * this.rows * this.columns; i++)
        {
            swap(rand.nextInt(4));
        }
    }

    public void swap(int direction)
    {
        if (game.getOptions().getInvertControls())
        {
            if (direction == 0) direction = 1;
            else if (direction == 1) direction = 0;
            else if (direction == 2) direction = 3;
            else direction = 2;
        }

        int emptyBoxID = this.emptyBox.getID();
        GameObject targetBox = null;
        switch(direction)
        {
            case 0: //Left
                if (emptyBoxID % this.columns != 0) targetBox = this.game.getGameObjectManager().searchByID(emptyBoxID - 1);
                break;

            case 1: //Right
                if (emptyBoxID % this.columns != this.columns - 1) targetBox = this.game.getGameObjectManager().searchByID(emptyBoxID + 1);
                break;

            case 2: //Down
                if (emptyBoxID < this.columns * (this.rows - 1)) targetBox = this.game.getGameObjectManager().searchByID(emptyBoxID + this.columns);
                break;

            case 3: //Up
                if (emptyBoxID > this.columns - 1) targetBox = this.game.getGameObjectManager().searchByID(emptyBoxID - this.columns);
                break;
        }
        if (targetBox == null) return;

        int emptyBoxX = this.emptyBox.getX();
        int emptyBoxY = this.emptyBox.getY();

        this.emptyBox.setID(targetBox.getID());
        this.emptyBox.setX(targetBox.getX());
        this.emptyBox.setY(targetBox.getY());

        targetBox.setID(emptyBoxID);
        targetBox.setX(emptyBoxX);
        targetBox.setY(emptyBoxY);
    }

    private void addHint()
    {
        this.hint = new Hint(this.image);
        this.game.getGameObjectManager().addGameObject(this.hint);
    }

    public void setHintVisibility()
    {
        if (!this.game.getOptions().getAllowHint()) return;

        if (this.showHint) this.showHint = false;
        else this.showHint = true;

        this.hint.setIsVisible(this.showHint);
    }
}