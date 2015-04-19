package com.example.deepak.messangerapp;

import android.content.ContentValues;
import android.os.Environment;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by deepak on 5/4/15.
 */
public class Contact {
    private static ArrayList<Contact> contacts;
    private static final String folder = Environment.getExternalStorageDirectory()+ File.separator+ "myapp" + File.separator;


    static {
        contacts = new ArrayList<Contact>();
        contacts.add(new Contact("Deepak", "My Messanger", "From", folder +"deepak.jpg"));
        contacts.add(new Contact("Dheeraj", "My Messanger", "From",folder +"dheeraj.jpg"));
        contacts.add(new Contact("Homu", "My Messanger", "From",folder +"homu.jpg"));
        contacts.add(new Contact("Naved", "My Messanger", "From",folder +"nqaved.jpg"));
        contacts.add(new Contact("Narendra Modi", "My Messanger", "From",folder +"narendramodi.jpg"));

        contacts.add(new Contact("Deepak", "My Messanger", "From", folder +"deepak.jpg"));
        contacts.add(new Contact("Dheeraj", "My Messanger", "From",folder +"dheeraj.jpg"));
        contacts.add(new Contact("Homu", "My Messanger", "From",folder +"homu.jpg"));
        contacts.add(new Contact("Naved", "My Messanger", "From",folder +"nqaved.jpg"));
        contacts.add(new Contact("Narendra Modi", "My Messanger", "From",folder +"narendramodi.jpg"));

        contacts.add(new Contact("Deepak", "My Messanger", "From", folder +"deepak.jpg"));
        contacts.add(new Contact("Dheeraj", "My Messanger", "From",folder +"dheeraj.jpg"));
        contacts.add(new Contact("Homu", "My Messanger", "From",folder +"homu.jpg"));
        contacts.add(new Contact("Naved", "My Messanger", "From",folder +"nqaved.jpg"));
        contacts.add(new Contact("Narendra Modi", "My Messanger", "From",folder +"narendramodi.jpg"));

    }

    public static ArrayList<Contact> getContacts() {
        return contacts;
    }


    private String name;
    private String status;
    private String from;
    private String image;

    public Contact(String name, String status, String from,String image) {
        this.name = name;
        this.status = status;
        this.from = from;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getFrom() {
        return from;
    }

    public String getImage() {
        return image;
    }
}
