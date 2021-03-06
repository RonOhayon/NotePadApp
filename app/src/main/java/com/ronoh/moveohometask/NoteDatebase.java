package com.ronoh.moveohometask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

public class NoteDatebase extends SQLiteOpenHelper {
    private  static final int DATABASE_VERSION = 2;
    private  static final String  DATABASE_NAME ="notedbs";
    private  static final String  DATABASE_TABLE ="noteTable";
    //columns name for database table
    private  static final String KEY_ID = "id";
    private  static final String KEY_TITLE = "title";
    private  static final String KEY_CONTENT="Content";
    private  static final String KEY_DATE = "date";
    private  static final String KEY_LOCATION = "location";

    NoteDatebase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE "+DATABASE_TABLE
                +"(" +
                KEY_ID + " INT PRIMARY KEY,"+
                KEY_TITLE + " TEXT,"+
                KEY_CONTENT + "TEXT,"+
                KEY_DATE + " TEXT, "+
                KEY_LOCATION +" TEXT "
                +")";
       db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion>= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
        onCreate(db);

    }

    public long addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE,note.getTitle());
        c.put(KEY_CONTENT,note.getContent());
        c.put(KEY_DATE,note.getDate());
        c.put(KEY_LOCATION,note.getLocation());

        long ID = db.insert(DATABASE_TABLE,null,c);

        return ID;
    }
   //get one row from SQL
    public Note getNote(long id){
        // select * from databaseTable where id = 1
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor  cursor = db.query(DATABASE_TABLE,new String[]{ KEY_ID,KEY_TITLE,KEY_CONTENT,KEY_DATE,KEY_LOCATION},KEY_ID+"?",
                new String[]{String.valueOf(id)},null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();

        return new Note(cursor.getLong(0),cursor.getString(1)
                ,cursor.getString(2),cursor.getString(3),cursor.getString(4));
    }
    //get the all list form database
    public List<Note> getNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNote = new ArrayList<>();
        // select * from database
        String query = "SELECT * FROM "+DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                Note note =  new Note();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setLocation(cursor.getString(4));
                allNote.add(note);
            }while (cursor.moveToNext());
        }
        return allNote;
    }

}
