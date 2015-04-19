package com.example.deepak.messangerapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class SettingActivity extends ActionBarActivity {
   private MysettingArrayAdapter mysettingArrayAdapter;
    private ListView listView;
   // String[] settingData = new String[]{"Help","Profile"};
    ArrayList<String> settingData = new ArrayList<String>();{
        settingData.add("Help");
        settingData.add("Profile");
        settingData.add("Account");
        settingData.add("Chat Setting");
        settingData.add("Notification");
        settingData.add("Contacts");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mysettingArrayAdapter = new MysettingArrayAdapter(this,R.layout.settingdatalist,settingData);
        listView = (ListView)findViewById(R.id.listviewsetting);
        listView.setAdapter(mysettingArrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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
