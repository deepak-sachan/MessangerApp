package com.example.deepak.messangerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by deepak on 1/4/15.
 */
public class UserChatFragment extends Fragment {
    ListView listView;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.user_list, container, false);

        // items = getActivity().getResources().getStringArray(R.array.test);
        listView = (ListView) view.findViewById(R.id.userdatalistview);


        DBHelper dbHelper = new DBHelper(getActivity());
        MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(),R.layout.user_data,dbHelper.getAllContacts());
      //  MyAdapter myAdapter = new MyAdapter(getActivity().getApplicationContext(),R.layout.user_data,Contact.getContacts());
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity().getApplication().getApplicationContext(),UserDisplayActivity.class));
                MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder) view.getTag();
                Toast.makeText(getActivity().getApplicationContext(),viewHolder.valueView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}
