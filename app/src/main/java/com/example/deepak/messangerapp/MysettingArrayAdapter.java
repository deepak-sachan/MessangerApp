package com.example.deepak.messangerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by deepak on 19/4/15.
 */
public class MysettingArrayAdapter extends ArrayAdapter {
     ArrayList<String> values;
    Context context;

    public MysettingArrayAdapter(Context context, int resource,ArrayList<String> values) {
        super(context, resource,values);
        this.context = context;
        this.values =values;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.settingdatalist,parent,false);
        TextView textView = (TextView)view.findViewById(R.id.label);
        ImageView imageView=(ImageView)view.findViewById(R.id.logo);
        textView.setText(values.get(position));
        final String s= values.get(position);
        if(s.equals("Help")){
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_help));

        }else if(s.equals("Profile")){
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_person));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, ProfileActivity.class));

                }
            });
        }else if(s.equals("Account")){
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_edit));
        }else if(s.equals("Chat Setting")){
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_chat));
        }else if(s.equals("Notification")){
            imageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_action_call));
        }else  {
            imageView.setImageResource(R.drawable.ic_action_user);
        }


        return view;
    }
}
