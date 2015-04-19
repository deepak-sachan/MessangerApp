package com.example.deepak.messangerapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by deepak on 16/4/15.
 */
public class UserContactFragment extends android.support.v4.app.Fragment {
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.user_contact_fragment,container,false);
        listView = (ListView)view.findViewById(R.id.usercontactlistview);
        DBHelper dbHelper = new DBHelper(getActivity());

        MyAdapter myAdapter= new MyAdapter(getActivity().getApplication().getApplicationContext(),R.layout.user_contact,dbHelper.getAllContacts());
         listView.setAdapter(myAdapter);
        return view;
    }
}

