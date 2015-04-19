package com.example.deepak.messangerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by deepak on 6/4/15.
 */
public class MyAdapterDispayUser extends ArrayAdapter<Message> {
    private ArrayList<Message> messageArrayAdapter;
    private Context context;
    private MediaController mediaController;

   // MediaPlayer mediaPlayer;
    private HashMap<Integer, AsyncTask> integerAsyncTaskHashMap = new HashMap<Integer, AsyncTask>();

    public MyAdapterDispayUser(Context context, int resource, ArrayList<Message> messageArrayAdapter) {
        super(context, resource, messageArrayAdapter);
        this.context = context;
        this.messageArrayAdapter = messageArrayAdapter;

    }

    static class ViewHolder {
        public TextView tvmymessage;
        public TextView tvothermessage;
        public ImageView imageView;
        public VideoView videoView;
        public int position;


    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {

        if (convertview == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = layoutInflater.inflate(R.layout.sms_row, parent, false);
            TextView tvmymessage = (TextView) rowView.findViewById(R.id.mymessage_text);
            final Message message = messageArrayAdapter.get(position);
            tvmymessage.setText(message.getMYMessage());
            TextView tvothermessage = (TextView) rowView.findViewById(R.id.othermessage_text);
            tvothermessage.setText(message.getOthermessage());

            ImageView imageView = (ImageView) rowView.findViewById(R.id.ivdisplayuser);
        /*Bitmap bmp = BitmapFactory.decodeFile(message.getImage());
        imageView.setImageBitmap(bmp);*/

           final VideoView videoView = (VideoView) rowView.findViewById(R.id.vvdisplyuser);
           final String uriPath = message.getVideo();
             
            mediaController = new MediaController(context);
            mediaController.setAnchorView(videoView);
            Uri uri = Uri.parse(uriPath);
            videoView.setMediaController(mediaController);

            videoView.setVideoURI(uri);
            videoView.requestFocus();



            // videoView.setVideoURI(Uri.parse(message.getVideo()));
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.tvmymessage = tvmymessage;
            viewHolder.tvothermessage = tvothermessage;
            viewHolder.imageView = imageView;
            viewHolder.videoView = videoView;



            MyDisplayAsynctask myDisplayAsynctask = new MyDisplayAsynctask(viewHolder);
            myDisplayAsynctask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, message.getImage());
            integerAsyncTaskHashMap.put(position, myDisplayAsynctask);
            rowView.setTag(viewHolder);

            return rowView;
        } else {

            final Message message = messageArrayAdapter.get(position);
            final ViewHolder viewHolder = (ViewHolder) convertview.getTag();
            AsyncTask asyncTask = integerAsyncTaskHashMap.remove(viewHolder.position);
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            MyDisplayAsynctask myDisplayAsynctask = new MyDisplayAsynctask(viewHolder);
            myDisplayAsynctask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, message.getImage());
            integerAsyncTaskHashMap.put(position, myDisplayAsynctask);
            viewHolder.tvmymessage.setText(message.getMYMessage());
            return convertview;
        }
    }

    private class MyDisplayAsynctask extends AsyncTask<String, Void, Bitmap> {
        private int pos;
        private ViewHolder viewHolder;

        public MyDisplayAsynctask(ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
            this.pos = viewHolder.position;
        }

        @Override
        protected void onPreExecute() {
            viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.loading));

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                return BitmapFactory.decodeFile(params[0]);
            } catch (Exception e) {
                Log.v("", "", e);
                return null;

            }

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (viewHolder.position == pos && result != null) {
                viewHolder.imageView.setImageBitmap(result);
            }
        }
    }


}
