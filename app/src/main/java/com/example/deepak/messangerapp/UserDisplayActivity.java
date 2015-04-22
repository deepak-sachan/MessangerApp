package com.example.deepak.messangerapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class UserDisplayActivity extends ActionBarActivity {
    private ListView listView;
    private  MyAdapterDispayUser myAdapterDispayUser;
    private ArrayList<Message> messageArrayList;
   private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_user_display);


         listView = (ListView)findViewById(R.id.list);

     DBHelper dbHelper = new DBHelper(this);
        messageArrayList = dbHelper.getAllMessages();
        myAdapterDispayUser = new MyAdapterDispayUser(this,R.layout.sms_row,messageArrayList);

     //MyAdapterDispayUser myAdapterDispayUser = new MyAdapterDispayUser(this,R.layout.sms_row,Message.getMessages());

        listView.setAdapter(myAdapterDispayUser);


    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();

            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);

            DBHelper dbHelper = new DBHelper(getApplicationContext());
            Message message =new Message("one","two",picturePath,"");
            dbHelper.insertMessages(message.getMYMessage(),message.getOthermessage(),message.getImage(),message.getVideo());
            myAdapterDispayUser.add(message);
            myAdapterDispayUser.notifyDataSetChanged();
            listView.smoothScrollToPosition(myAdapterDispayUser.getCount()-1);

            cursor.close();


        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_display, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.selectimage:
                Intent i = new Intent(

                        Intent.ACTION_PICK,

                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;
            case R.id.action_settings:
                startActivity(new Intent(this,SettingActivity.class));

        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
