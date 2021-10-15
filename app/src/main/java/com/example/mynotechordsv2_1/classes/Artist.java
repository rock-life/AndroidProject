package com.example.mynotechordsv2_1.classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Artist extends SQLiteOpenHelper {

    String Name;
    int NumberOfSongs;
    boolean IsSelected;

    public void setName(String name) {
        Name = name;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        NumberOfSongs = numberOfSongs;
    }

    public void setSelected(boolean selected) {
        IsSelected = selected;
    }

    public int getNumberOfSongs() {
        return NumberOfSongs;
    }

    public String getName() {
        return Name;
    }
    public boolean setSelected() {
        return IsSelected;
    }

    private static final String DATABASE_NAME = "artist.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE = "artist";
    private Context context;
    public Artist(Context context)
    {
        super(context,DATABASE_NAME,null, SCHEMA );
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase bd)
    {
        try {

            bd.execSQL("create table artist ( _id integer primary key autoincrement, Name text );");
        }
        catch (Exception e){}
    }
    @Override
    public  void onUpgrade(SQLiteDatabase bd, int oldV, int newV)
    {
        bd.execSQL("drop table if exists "+TABLE+";");
    }
    public String add(SQLiteDatabase bd,Cursor cursor, String name)
    {
        cursor= bd.rawQuery("select Name from artist;", null);
        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                if(name.equals(cursor.getString(cursor.getColumnIndex("Name"))))
                    return "Виконавець існує!";
                cursor.moveToNext();
            }
        }
        bd.execSQL("insert into artist (Name) values ('" +name+ "');");
       return "Виконавця успішно додано!";
    }
}
