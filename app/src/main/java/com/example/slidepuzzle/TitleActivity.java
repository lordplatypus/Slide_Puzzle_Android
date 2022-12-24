package com.example.slidepuzzle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sp;
    private Options options;
    private ActivityResultLauncher<Intent> arl;
    private int numberColor;
    private int outlineColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);

        this.sp = getSharedPreferences("Options", MODE_PRIVATE);
        //this.options = new Options();
        restoreOptions();

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
    }

    private void restoreOptions()
    {
        this.options = new Options(
                this.sp.getString("imagePath", null),
                this.sp.getInt("rows", 5),
                this.sp.getInt("columns", 5),
                this.sp.getBoolean("allowNumber", false),
                this.sp.getInt("numberColor", 0),
                this.sp.getInt("numberSize", 16),
                this.sp.getBoolean("allowOutline", false),
                this.sp.getInt("outlineColor", 0),
                this.sp.getInt("outlineSize", 10),
                this.sp.getBoolean("allowHint", false),
                this.sp.getBoolean("invertControls", false),
                this.sp.getBoolean("randomStart", false)
        );
        this.numberColor = this.options.getNumberColor();
        this.outlineColor = this.options.getOutlineColor();
        restoreUI();
    }

    private void restoreUI()
    {
        ImageView img = findViewById(R.id.image_preview);
        img.setImageBitmap(BitmapFactory.decodeFile(this.options.getImagePath()));

        EditText rows = findViewById(R.id.rows_number);
        rows.setText(Integer.toString(this.options.getRows()));

        EditText columns = findViewById(R.id.columns_number);
        columns.setText(Integer.toString(this.options.getColumns()));

        CheckBox bool = findViewById(R.id.display_numbers_check_box);
        bool.setChecked(this.options.getAllowNumber());

        View colorView = (View) findViewById(R.id.number_color_view);
        colorView.setBackgroundColor(this.options.getNumberColor());

        EditText numberSize = findViewById(R.id.number_size_number);
        numberSize.setText(Integer.toString(this.options.getNumberSize()));

        bool = findViewById(R.id.display_outline_check_box);
        bool.setChecked(this.options.getAllowOutline());

        colorView = (View) findViewById(R.id.outline_color_view);
        colorView.setBackgroundColor(this.options.getOutlineColor());

        EditText outlineSize = findViewById(R.id.outline_size_number);
        outlineSize.setText(Integer.toString(this.options.getOutlineSize()));

        bool = findViewById(R.id.invert_controls_check_box);
        bool.setChecked(this.options.getInvertControls());

        bool = findViewById(R.id.allow_hint_check_box);
        bool.setChecked(this.options.getAllowHint());

        bool = findViewById(R.id.randomize_start_check_box);
        bool.setChecked(this.options.getRandomStart());

    }

    public void saveOptions(View view)
    {
        SharedPreferences.Editor editor = this.sp.edit();

        editor.putString("imagePath", null);
        EditText rows = findViewById(R.id.rows_number);
        editor.putInt("rows", Integer.parseInt(rows.getText().toString()));
        EditText columns = findViewById(R.id.columns_number);
        editor.putInt("columns", Integer.parseInt(columns.getText().toString()));
        CheckBox bool = findViewById(R.id.display_numbers_check_box);
        editor.putBoolean("allowNumber", bool.isChecked());
        editor.putInt("numberColor", this.numberColor);
        EditText numberSize = findViewById(R.id.number_size_number);
        editor.putInt("numberSize", Integer.parseInt(numberSize.getText().toString()));
        bool = findViewById(R.id.display_outline_check_box);
        editor.putBoolean("allowOutline", bool.isChecked());
        editor.putInt("outlineColor", this.outlineColor);
        EditText outlineSize = findViewById(R.id.outline_size_number);
        editor.putInt("outlineSize", Integer.parseInt(outlineSize.getText().toString()));
        bool = findViewById(R.id.invert_controls_check_box);
        editor.putBoolean("invertControls", bool.isChecked());
        bool = findViewById(R.id.allow_hint_check_box);
        editor.putBoolean("allowHint", bool.isChecked());
        bool = findViewById(R.id.randomize_start_check_box);
        editor.putBoolean("randomStart", bool.isChecked());

        editor.commit();

        Toast.makeText(this, "Settings Saved", Toast.LENGTH_SHORT).show();
    }

    public void startGame(View view)
    {
        if (this.options.getImagePath() == null)
        { //An image needs to be selected
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent main = new Intent(this, MainActivity.class);

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