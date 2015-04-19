package com.example.deepak.messangerapp;

import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

/**
 * Message is a Custom Object to encapsulate message information/fields
 * 
 * @author Adil Soomro
 *
 */
public class Message {
    private static ArrayList<Message> messages;
    private static final String folder = Environment.getExternalStorageDirectory() + File.separator + "myapp" + File.separator;

   // private static final String foldervideo = Environment.getExternalStorageDirectory() + File.separator;

    static {
        messages = new ArrayList<Message>();
        messages.add(new Message("Hi How are u", "i am fine", folder + "dheeraj.jpg", folder + "deepak.mp4" ));
        messages.add(new Message("where are you", "i am in kanpur", folder + "deepak.jpg", folder + "deepak.mp4"));


    }



    public static ArrayList<Message> getMessages(){
        return messages;
    }


    private String mymessage;
    private String othermessage;
    private String image;
    private String video;





    public Message(String mymessage,String othermessage,String image, String video ) {
        super();
        this.mymessage = mymessage;
        this.othermessage=othermessage;

        this.image = image;
        this.video = video;


    }


    public String getMYMessage() {
        return mymessage;
    }
    public String getOthermessage() {
        return othermessage;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return video;
    }

}
