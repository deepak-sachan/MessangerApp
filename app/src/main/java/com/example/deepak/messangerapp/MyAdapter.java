package com.example.deepak.messangerapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by deepak on 1/4/15.
 */
public class MyAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> chatDatas;
    private Context context;
    private HashMap<Integer, AsyncTask> integerAsyncTaskHashMap = new HashMap<Integer, AsyncTask>();

    public MyAdapter(Context context, int resource, ArrayList<Contact> chatDatas1) {
        super(context, resource, chatDatas1);
        this.context = context;
        this.chatDatas = chatDatas1;
    }

    static class ViewHolder {
        public TextView valueView;
        public ImageView imgIcon;
        public TextView from;
        public TextView status;
        public int position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = layoutInflater.inflate(R.layout.user_data, parent, false);

            final Contact contact = chatDatas.get(position);

            TextView valueView = (TextView) rowView.findViewById(R.id.username);
            valueView.setText(contact.getName());
            final ImageView imgIcon = (ImageView) rowView.findViewById(R.id.circle_background);
            TextView status = (TextView)rowView.findViewById(R.id.status);
            status.setText(contact.getStatus());
           /* TextView from=(TextView)rowView.findViewById(R.id.from);
            from.setText(DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_12HOUR));
*/
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.valueView = valueView;
            viewHolder.imgIcon = imgIcon;
            viewHolder.position = position;
            viewHolder.status = status;


            MyAsyncTask myAsyncTask = new MyAsyncTask(viewHolder);
            myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,contact.getImage());
            integerAsyncTaskHashMap.put(position,myAsyncTask);

            rowView.setTag(viewHolder);

            return rowView;
        } else {
            final Contact contact = chatDatas.get(position);
            final ViewHolder viewHolder = (ViewHolder) convertView.getTag();

            AsyncTask asyncTask = integerAsyncTaskHashMap.remove(viewHolder.position);
            if(asyncTask != null){
                asyncTask.cancel(true);
            }

            viewHolder.position = position;
            MyAsyncTask myAsyncTask = new MyAsyncTask(viewHolder);
            myAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,contact.getImage());
            integerAsyncTaskHashMap.put(position,myAsyncTask);
            viewHolder.valueView.setText(contact.getName());
            return convertView;
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private int pos;
        private ViewHolder viewHolder;

        private MyAsyncTask(ViewHolder viewHolder){
            this.viewHolder = viewHolder;
            this.pos = viewHolder.position;
        }

        @Override
        protected void onPreExecute() {
            viewHolder.imgIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.loading));
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                return BitmapFactory.decodeFile(params[0]);
/*
                        return Bitmap.createScaledBitmap(BitmapFactory.decodeFile(contact.getImage()), 60, 60, false);
*/
            } catch (Exception e) {
                Log.e("", "", e);
                return null;
            }

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null && viewHolder.position == pos) {
                viewHolder.imgIcon.setImageBitmap(result);
            }
        }


    }
}