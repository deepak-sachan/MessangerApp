package com.example.deepak.messangerapp;

import java.util.ArrayList;

/**
 * Created by deepak on 21/4/15.
 */
public class Profile {
    private String profileimage;
    private String profiletext;

    public Profile(String profileimage, String profiletext) {
        this.profileimage = profileimage;
        this.profiletext = profiletext;
    }

    public String getProfileimage() {
        return profileimage;
    }


    public String getProfiletext() {
        return profiletext;
    }


}