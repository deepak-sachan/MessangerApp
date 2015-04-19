package com.example.deepak.messangerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by deepak on 4/4/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    private int oldVersion;
    private static final int newVersion = 12;
    private SQLiteDatabase db;

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String MESSAGES_TABLE_NAME = "messages";


    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_STATUS = "status";
    public static final String CONTACTS_COLUMN_FROM = "from";
    public static final String CONTACTS_COLUMN_IMAGE = "image";

    public static final String MESSAGES_COLUMN_ID = "id";
    public static final String MESSAGES_COLUMN_MYMESSAGE_NAME = "mymessage";
    public static final String MESSAGES_COLUMN_OTHERMESSAGE = "othermessage";
    public static final String MESSAGES_COLUMN_MESSAGEIMAGE = "image";
    public static final String MESSAGES_COLUMN_MESSAGEVIDEO = "video";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, newVersion);
        db = this.getWritableDatabase();
        if (newVersion > oldVersion) {
            /*insertAllContacts();
            insertAllMessages();*/
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table contacts (id integer primary key, name text,status text,`from` text,image text)");
        db.execSQL("create table messages (id integer primary key, mymessage text,othermessage text, image text,video text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.oldVersion = oldVersion;
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS messages");
        onCreate(db);
    }

    public boolean insertcontacts(String name, String status, String from, String image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("status", status);
        contentValues.put("`from`", from);
        contentValues.put("image", image);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public void insertAllContacts() {
        for (Contact contact : Contact.getContacts()) {
            insertcontacts(contact.getName(), contact.getStatus(), contact.getFrom(), contact.getImage());
        }
    }

    public Cursor getdata(int id) {
        Cursor cursor = db.rawQuery("select * from contacts where id=" + id + "", null);
        return cursor;

    }

    public int NumofRows() {
        int numrows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numrows;
    }

    public boolean updatecontacts(int id, String name, String status, String from) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("status", status);
        contentValues.put("`from`", from);
        db.update("contacts", contentValues, "id= ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public int deletecontacs(int id) {
        return db.delete("contacts", "id= ? ", new String[]{Integer.toString(id)});

    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactArrayList = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contacts", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            contactArrayList.add(new Contact(
                    cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_STATUS)),
                    cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_FROM)),
                    cursor.getString(cursor.getColumnIndex(CONTACTS_COLUMN_IMAGE))
            ));


            cursor.moveToNext();
        }
        return contactArrayList;
    }

    public ArrayList<Message> getAllMessages() {
        ArrayList<Message> messageArrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from messages", null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            messageArrayList.add(new Message(
                    cursor.getString(cursor.getColumnIndex(MESSAGES_COLUMN_MYMESSAGE_NAME)),
                    cursor.getString(cursor.getColumnIndex(MESSAGES_COLUMN_OTHERMESSAGE)),
                    cursor.getString(cursor.getColumnIndex(MESSAGES_COLUMN_MESSAGEIMAGE)),
                    cursor.getString(cursor.getColumnIndex(MESSAGES_COLUMN_MESSAGEVIDEO))

            ));


            cursor.moveToNext();
        }
        return messageArrayList;
    }


    public boolean insertMessages(String mymessage, String othermessage, String image, String video) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mymessage", mymessage);
        contentValues.put("othermessage", othermessage);
        contentValues.put("image", image);
        contentValues.put("video", video);

        db.insert("messages", null, contentValues);
        return true;
    }

    public void insertAllMessages() {
        for (Message message : Message.getMessages()) {
            insertMessages(message.getMYMessage(), message.getOthermessage(), message.getImage(), message.getVideo());
        }

    }
}
