package com.example.slidepuzzle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import yuku.ambilwarna.AmbilWarnaDialog;

public class TitleActivity extends AppCompatActivity
{
    private Options options;
    private ActivityResultLauncher<Intent> arl;
    private int numberColor;
    private int outlineColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);
        this.options = new Options();

//        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//        {}
//        else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE))
//        {}
//        else
//        {
//            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//        }


        this.arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), uri ->
        {
            if (uri.getResultCode() == RESULT_OK && uri.getData() != null)
            {
                Intent data = uri.getData();
                try
                {
                    String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    this.options.setImagePath(cursor.getString(column_index));
                    Log.v("Image Path", this.options.getImagePath());

                    ImageView img = findViewById(R.id.image_preview);
                    img.setImageBitmap(BitmapFactory.decodeFile(this.options.getImagePath()));

                    if (cursor != null) cursor.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        this.numberColor = 0;
        this.outlineColor = 0;
    }

    public void startGame(View view)
    {
        if (this.options.getImagePath() == null)
        { //An image needs to be selected
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent main = new Intent(this, MainActivity.class);
//        EditText rows = (EditText) findViewById(R.id.rows_number);
//        this.options.setRows(Integer.parseInt(rows.getText().toString()));
//        EditText columns = (EditText) findViewById(R.id.columns_number);
//        this.options.setColumns(Integer.parseInt(columns.getText().toString()));
//        CheckBox bool = (CheckBox) findViewById(R.id.display_numbers_check_box);
//        this.options.setAllowNumber(bool.isChecked());
//        this.options.setNumberColor(this.numberColor);
//        EditText numberSize = (EditText) findViewById(R.id.number_size_number);
//        this.options.setNumberSize(Integer.parseInt(numberSize.getText().toString()));
//        bool = (CheckBox) findViewById(R.id.display_outline_check_box);
//        this.options.setAllowOutline(bool.isChecked());
//        EditText outlineSize = (EditText) findViewById(R.id.outline_size_number);
//        this.options.setOutlineSize(Integer.parseInt(outlineSize.getText().toString()));
//        bool = (CheckBox) findViewById(R.id.invert_controls_check_box);
//        this.options.setInvertControls(bool.isChecked());
//        bool = (CheckBox) findViewById(R.id.allow_hint_check_box);
//        this.options.setAllowHint(bool.isChecked());
//        bool = (CheckBox) findViewById(R.id.randomize_start_check_box);
//        this.options.setRandomStart(bool.isChecked());
//
//        main.putExtra("Options", this.options);

        main.putExtra("ImagePath", this.options.getImagePath());
        EditText rows = findViewById(R.id.rows_number);
        main.putExtra("Rows", Integer.parseInt(rows.getText().toString()));
        EditText columns = findViewById(R.id.columns_number);
        main.putExtra("Columns", Integer.parseInt(columns.getText().toString()));
        CheckBox bool = findViewById(R.id.display_numbers_check_box);
        main.putExtra("Numbers", bool.isChecked());
        main.putExtra("NumbersColor", this.numberColor);
        EditText numberSize = findViewById(R.id.number_size_number);
        main.putExtra("NumbersSize", Integer.parseInt(numberSize.getText().toString()));
        bool = findViewById(R.id.display_outline_check_box);
        main.putExtra("Outline", bool.isChecked());
        main.putExtra("OutlineColor", this.outlineColor);
        EditText outlineSize = findViewById(R.id.outline_size_number);
        main.putExtra("OutlineSize", Integer.parseInt(outlineSize.getText().toString()));
        bool = findViewById(R.id.invert_controls_check_box);
        main.putExtra("Invert", bool.isChecked());
        bool = findViewById(R.id.allow_hint_check_box);
        main.putExtra("Hint", bool.isChecked());
        bool = findViewById(R.id.randomize_start_check_box);
        main.putExtra("Random", bool.isChecked());
        startActivity(main);
    }

    public void setImage(View view)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        this.arl.launch(intent);
    }

    public void setNumberColor(View view)
    {
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(this, numberColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener()
                {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {}

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color)
                    {
                        View colorView = (View) findViewById(R.id.number_color_view);
                        colorView.setBackgroundColor(color);
                        numberColor = color;
                    }
                });
        colorPickerDialogue.show();
    }

    public void setOutlineColor(View view)
    {
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(this, outlineColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener()
                {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {}

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color)
                    {
                        View colorView = (View) findViewById(R.id.outline_color_view);
                        colorView.setBackgroundColor(color);
                        outlineColor = color;
                    }
                });
        colorPickerDialogue.show();
    }
}