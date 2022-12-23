package com.example.slidepuzzle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity
{
    private Options options;
    private ActivityResultLauncher<Intent> test;
    private Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.options = new Options();

        retrieveData();
        this.setContentView(new GameSurface(this, this.options));
    }

    private void retrieveData()
    {
        //this.options = getIntent().getParcelableExtra("Options");
        this.options.setImagePath(getIntent().getStringExtra("ImagePath"));
        this.options.setRows(getIntent().getIntExtra("Rows", 0));
        this.options.setColumns(getIntent().getIntExtra("Columns", 0));

        this.options.setAllowNumber(getIntent().getBooleanExtra("Numbers", false));
        this.options.setNumberColor(getIntent().getIntExtra("NumbersColor", 0));
        this.options.setNumberSize(getIntent().getIntExtra("NumbersSize", 0));
        this.options.setAllowOutline(getIntent().getBooleanExtra("Outline", false));
        this.options.setOutlineColor(getIntent().getIntExtra("OutlineColor", 0));
        this.options.setOutlineSize(getIntent().getIntExtra("OutlineSize", 0));
        this.options.setInvertControls(getIntent().getBooleanExtra("Invert", false));
        this.options.setAllowHint(getIntent().getBooleanExtra("Hint", false));
        this.options.setRandomStart(getIntent().getBooleanExtra("Random", false));
    }
}