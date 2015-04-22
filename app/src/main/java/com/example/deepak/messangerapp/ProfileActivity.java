package com.example.deepak.messangerapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ProfileActivity extends ActionBarActivity {
 private    ArrayList<Profile> profileArrayList;
    TextView textView;
    DBHelper dbHelper;
   private ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textView = (TextView) findViewById(R.id.tvprofilename);
        imageView1 = (ImageView) findViewById(R.id.ivprofilepicture);
        ImageView imageView2 = (ImageView) findViewById(R.id.ivprofile);

        dbHelper = new DBHelper(getApplicationContext());
        if(dbHelper .getAllProfile() != null) {
            Cursor c = dbHelper.getAllProfile();
            if (c.moveToFirst()) {
                do {
                    String image=c.getString(0);
                    String text = c.getString(1);
                    textView.setText(text);
                    imageView2 .setImageBitmap(BitmapFactory.decodeFile(image));
                } while (c.moveToNext());
            }
            c.close();
        }


        ImageView imageView = (ImageView) findViewById(R.id.imageviewprofiletext);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileTextActivity.class);
                startActivityForResult(intent, 1);

            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);*/


                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 2);


            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
         String image ="",text1="";
        dbHelper = new DBHelper(getApplicationContext());
        if (dbHelper.getAllProfile() != null) {
            Cursor c = dbHelper.getAllProfile();
            if (c.moveToFirst()) {
                do {
                    image = c.getString(0);
                    text1 = c.getString(1);


                } while (c.moveToNext());
            }
            c.close();
        }
            dbHelper.deleteaAllProfiles();


            if (requestCode == 1) {
                String message = data.getStringExtra("MESSAGE");

                Profile profile = new Profile(image, message);
                String s = profile.getProfileimage();
                dbHelper.insertProfile(s, message);
                textView.setText(message);
                //


            } else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();
                String picturePath = getpath(selectedImage);

                Profile profile = new Profile(picturePath, text1);
                String text = profile.getProfiletext();
                dbHelper.insertProfile(picturePath, text);

                ImageView imageView2 = (ImageView) findViewById(R.id.ivprofile);
                imageView2.setImageBitmap(BitmapFactory.decodeFile(profile.getProfileimage()));
            }
        }
    public String getpath(Uri uri){
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(uri,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        return cursor.getString(columnIndex);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
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
}
