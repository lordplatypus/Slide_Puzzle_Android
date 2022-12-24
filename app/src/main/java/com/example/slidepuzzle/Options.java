package com.example.slidepuzzle;

public class Options
{
    private String imagePath;
    private int rows;
    private int columns;
    private boolean allowNumber;
    private int numberColor;
    private int numberSize;
    private boolean allowOutline;
    private int outlineColor;
    private int outlineSize;
    private boolean allowHint;
    private boolean invertControls;
    private boolean randomStart;

    public Options()
    {
        this.imagePath = null;
        this.rows = 5;
        this.columns = 5;
        this.allowNumber = false;
        this.numberColor = 0;
        this.numberSize = 16;
        this.allowOutline = false;
        this.outlineColor = 0;
        this.outlineSize = 10;
        this.allowHint = false;
        this.invertControls = false;
        this.randomStart = false;
    }

    public Options(String imagePath, int rows, int columns, boolean allowNumber, int numberColor,
                   int numberSize, boolean allowOutline, int outlineColor, int outlineSize, boolean allowHint,
                   boolean invertControls, boolean randomStart)
    {
        this.imagePath = imagePath;
        this.rows = rows;
        this.columns = columns;
        this.allowNumber = allowNumber;
        this.numberColor = numberColor;
        this.numberSize = numberSize;
        this.allowOutline = allowOutline;
        this.outlineColor = outlineColor;
        this.outlineSize = outlineSize;
        this.allowHint = allowHint;
        this.invertControls = invertControls;
        this.randomStart = randomStart;
    }

    public String getImagePath()
    {
        return this.imagePath;
    }

    public void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    public int getRows()
    {
        return this.rows;
    }

    public void setRows(int rows)
    {
        this.rows = rows;
    }

    public int getColumns()
    {
        return this.columns;
    }

    public void setColumns(int columns)
    {
        this.columns = columns;
    }

    public boolean getAllowNumber()
    {
        return this.allowNumber;
    }

    public void setAllowNumber(boolean allowNumber)
    {
        this.allowNumber = allowNumber;
    }

    public int getNumberColor()
    {
        return this.numberColor;
    }

    public void setNumberColor(int numberColor)
    {
        this.numberColor = numberColor;
    }

    public int getNumberSize()
    {
        return this.numberSize;
    }

    public void setNumberSize(int numberSize)
    {
        this.numberSize = numberSize;
    }

    public boolean getAllowOutline()
    {
        return this.allowOutline;
    }

    public void setAllowOutline(boolean allowOutline)
    {
        this.allowOutline = allowOutline;
    }

    public int getOutlineColor()
    {
        return this.outlineColor;
    }

    public void setOutlineColor(int outlineColor)
    {
        this.outlineColor = outlineColor;
    }

    public int getOutlineSize()
    {
        return this.outlineSize;
    }

    public void setOutlineSize(int outlineSize)
    {
        this.outlineSize = outlineSize;
    }

    public boolean getAllowHint()
    {
        return this.allowHint;
    }

    public void setAllowHint(boolean allowHint)
    {
        this.allowHint = allowHint;
    }

    public boolean getInvertControls()
    {
        return this.invertControls;
    }

    public void setInvertControls(boolean invertControls)
    {
        this.invertControls = invertControls;
    }

    public boolean getRandomStart()
    {
        return this.randomStart;
    }

    public void setRandomStart(boolean randomStart)
    {
        this.randomStart = randomStart;
    }
}