package com.example.slidepuzzle;

import java.util.ArrayList;
import android.graphics.Canvas;

public class GameObjectManager
{
    private ArrayList<GameObject> gameObjects;

    public GameObjectManager()
    {
        this.gameObjects = new ArrayList<GameObject>();
    }

    public void update(int deltaTime)
    {
        this.gameObjects.forEach((gameObject) -> gameObject.update(deltaTime));
    }

    public void draw(Canvas canvas)
    {
        this.gameObjects.forEach((gameObject) -> gameObject.draw(canvas));
    }

    public void addGameObject(GameObject gameObject)
    {
        this.gameObjects.add(gameObject);
    }

    public GameObject searchByID(int id)
    {
        for (int i = 0; i < this.gameObjects.size(); i++)
        {
            GameObject go = this.gameObjects.get(i);
            if (go.getID() == id) return go;
        }
        return null;
    }

    public GameObject searchByName(String name)
    {
        for (int i = 0; i < this.gameObjects.size(); i++)
        {
            GameObject go = this.gameObjects.get(i);
            if (go.getName() == name) return go;
        }
        return null;
    }

    public GameObject searchByTag(String tag)
    {
        for (int i = 0; i < this.gameObjects.size(); i++)
        {
            GameObject go = this.gameObjects.get(i);
            if (go.getTag() == tag) return go;
        }
        return null;
    }

    public void clear()
    {
        this.gameObjects.clear();
    }
}