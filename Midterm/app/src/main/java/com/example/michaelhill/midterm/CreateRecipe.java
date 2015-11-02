package com.example.michaelhill.midterm;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class CreateRecipe extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    int n = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_recipe);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_recipe, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView iv = (ImageView) findViewById(R.id.recipeImg);
            iv.setImageBitmap(imageBitmap);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void chooseimage(View view){



    }

    public void takePic(View view){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }

    }

    public void saveImage(Bitmap bitmap)
    {
        OutputStream fOut = null;
        Uri outputFileUri = null;
        try
        {
            File root = new File(Environment.getExternalStorageDirectory() +
                    File.separator + "Pictures" + File.separator);
            root.mkdirs();
            File imagePath = new File(root, "image-"+n+".jpg");
            outputFileUri = Uri.fromFile(imagePath);
            fOut = new FileOutputStream(imagePath);
        }
        catch (Exception e)
        {
            Context context = getApplicationContext();
            String text = "Error in Saving Image";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        }
        catch (Exception e)
        {
            Context context = getApplicationContext();
            String text = "Error in Compressing Image";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        try
        {
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap,"image.jpg", " ");
        }
        catch (Exception e)
        {
            Context context = getApplicationContext();
            String text = "Missing Write Permission";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        Log.i("CreateRecipeActivity", outputFileUri.toString());
        n++;

    }


}
