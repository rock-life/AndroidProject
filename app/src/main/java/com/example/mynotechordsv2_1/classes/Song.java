package com.example.mynotechordsv2_1.classes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class Song extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "artist.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    public int id_somg;
    public String id_artist;
    private String id_songs[];
   private char []song;
   private boolean if_is=false;
   private char []Note={'A','B','C','D','E','F','G'};
    private Context context;
    static final String TABLE = "Song";
    private SimpleCursorAdapter adapter;

    public Song(Context context)
    {
        super(context, DATABASE_NAME, null,SCHEMA);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase bd)
    {
        try{
            bd.execSQL("create table Song (_id integer primary key autoincrement, artist integer ,name text,  text text, foreign key(artist) references artist(_id) )");
        }
        catch (Exception e){};
    }
    @Override
    public void onUpgrade(SQLiteDatabase bd, int old, int newxersion)
    {
        bd.execSQL("drop table if exists Song");
    }

    private void getIdSongs(Cursor cursor, SQLiteDatabase db)
    {
        int i=0;
        cursor=db.rawQuery("select _id from Song", null);
        id_songs=new String[cursor.getCount()];
        if(cursor.moveToFirst())
        while (!cursor.isAfterLast())
        {
            id_songs[i]=cursor.getString(cursor.getColumnIndex("_id"));
            cursor.moveToNext();
            i++;
        }

    }
    public String add(SQLiteDatabase db, Cursor cursor,String artist,  String name, String text)
    {
        try {
            cursor = db.rawQuery("select * from artist where Name like'" + artist + "'", null);
            if(cursor.moveToFirst())
            {
                while (!cursor.isAfterLast())
                {
                    id_artist = cursor.getString(cursor.getColumnIndex("_id"));
                    cursor.moveToNext();
                }
            }
            db.execSQL("insert into Song(artist,name,text) values (" + id_artist + ",'" + name + "','" + text + "')");
        }
        catch (Exception e)
        {
            return  e.getMessage();
        }
        return "Успішно додано";
    }
    public void deleteSong(Cursor cursor,SQLiteDatabase db,int id)
    {
        getIdSongs(cursor,db);
        for (int i=0;i<id_songs.length;i++)
            if(i==id){
                id=Integer.parseInt(id_songs[i]);
            break;}
        db.execSQL("delete from Song where _id="+id+";");
    }
    public void getListSong(ListView listView, SQLiteDatabase db, Cursor cursor)
    {
        Toast toast;
        try {
            String[] headers = new String[]{"Name", "name","_id"};
            cursor = db.rawQuery("select Song._id, artist.Name, Song.name  from Song inner join artist on artist._id=Song.artist", null);

            listView.setAdapter(adapter);
        }
        catch (Exception e){
            toast=Toast.makeText(context, e.getMessage().toString(),Toast.LENGTH_LONG);
            toast.show();
        }
    }
    public void getSong(int id, SQLiteDatabase bd, Cursor cursor, TextView name, TextView artist, TextView text)
    {
        Toast toast;
        try{
            getIdSongs(cursor,bd);
            for (int i=0;i<id_songs.length;i++)
                if(i==id){
                    id=Integer.parseInt(id_songs[i]);
                break;}
            cursor=bd.rawQuery("select Song._id, artist.Name, Song.name ,Song.text from Song inner join artist on artist._id=Song.artist where Song._id="+id+";", null);
           if(cursor.moveToFirst()){
               name.setText(cursor.getString(cursor.getColumnIndex("name")));
            text.setText(cursor.getString(cursor.getColumnIndex("text")));
            artist.setText(cursor.getString(cursor.getColumnIndex("Name")));}
        }
        catch (Exception ex){
            toast=Toast.makeText(context,ex.getMessage().toString(),Toast.LENGTH_LONG); toast.show();};
    };
    public String getTextEditTone ( char[] song_exist,String up_or_down  ) {
        song = new char[song_exist.length + 300];
        int j, v=0;
        for (int i = 0; i < song_exist.length; i++)
        {
            if(i>=song_exist.length-1)
                break;
            for(j=0;j<Note.length;j++)
            {
                if (song_exist[i] ==Note[j])
                {

                        if(i==0)
                        {
                            if_is=ifEditSong(song_exist,i);
                            break;
                        }
                        else{
                             if (song_exist[i - 1] == ' ' || song_exist[i - 1] == '\n' || song_exist[i - 1] == '\t')
                             {  if_is = ifEditSong(song_exist, i);
                             break;}
                             else {
                                 if_is=false;
                                 break;
                             }
                }

                }
            }
            if (if_is==false)
            {
            song[v]=song_exist[i];
            }
            else
            {
                   if (up_or_down.equals("+"))
                {
                    if (song_exist[i + 1] == '#')
                {
                    int t=Note.length;
                    if(j==Note.length-1)
                    {
                        song[v] = Note[0];
                        i++;
                    }
                    else
                    {song[v] = Note[j+1];
                        i++;}
                }
                   else if(song_exist[i]=='E')
                    {
                        song[v]='F';
                    }
                   else if(song_exist[i]=='B')
                    {
                        song[v]='C';
                    }
                   else {
                          if (song_exist[i + 1] == 'b')
                          {
                                  song[v] = song_exist[i];
                                  i++;

                          }

                          else
                              {
                                  song[v]=song_exist[i];
                                  v++;
                                  song[v]='#';
                              }
                   }
                   if_is=false;
                }
                else if(up_or_down.equals("-")) {
                    {
                        if (song_exist[i + 1] == '#') {
                        song[v] = song_exist[i];
                        i++;
                    }
                        else if (song_exist[i] == 'F') {
                            song[v] = 'E';
                        } else if (song_exist[i] == 'C') {
                            song[v] = 'B';
                        } else {
                            if (song_exist[i + 1] == 'b') {
                                if(j==0)
                                {
                                    song[v] = Note[Note.length-1];
                                    i++;
                                }
                                else {
                                    song[v] = Note[j - 1];
                                    i++;
                                }
                            }
                            else {
                                song[v] = song_exist[i];
                                v++;
                                song[v] = 'b';
                            }
                        }
                        if_is=false;
                    }
                }
    }
            v++;
}
        return String.valueOf(song);
    }
    private boolean ifEditSong(char [] s_e, int i)
    {
        boolean is_alteration=false;
        switch (s_e[i+1])
        {
            case 'b': is_alteration=true; return otherPropertiesChord(is_alteration,s_e,i);
            case '#': is_alteration=true; return otherPropertiesChord(is_alteration,s_e,i);
            default:return otherPropertiesChord(is_alteration,s_e,i);
        }
    }
    private boolean otherPropertiesChord(boolean alt,char []s_e, int i)
    {
        int k=i;
        if(i>s_e.length-3&&i<s_e.length)
            return false;
        if(alt==true)
            k= k+1;
        if(s_e[k+1]=='m')
        {
            if (s_e[k+2]==' '||s_e[k+2]=='\n'||s_e[k+2]=='\t')
                return true;
            if(s_e[k+2]>0&&s_e[k+2]<+9)
            return true;
            if(s_e[k+2]=='m'&&s_e[k+3]=='a'&&s_e[k+4]=='j')
                return true;

        }
        else {
        if (s_e[k+1]==' '||s_e[k+1]=='\n'||s_e[k+1]=='\t')
            return true;
        if(s_e[k+1]=='d'&&s_e[k+2]=='i'&&s_e[k+3]=='m')
            return true;
        if(s_e[k+1]=='s'&&s_e[k+2]=='u'&&s_e[k+3]=='s')
            return true;
        if(Character.isDigit(s_e[k+1]))
                return true;
        }
        return false;
    }

    public void getToEditSong(Cursor c, SQLiteDatabase bd, int id, EditText name_artist, EditText name_song, EditText text) {
    try{
        getIdSongs(c,bd);
        for (int i=0;i<id_songs.length;i++)
            if(i==id){
                id=Integer.parseInt(id_songs[i]);
            break;}
            c=bd.rawQuery("select Song._id, artist.Name, Song.name ,Song.text from Song inner join artist on artist._id=Song.artist where Song._id="+id+";", null);
        if(c.moveToFirst()){
            name_song.setText(c.getString(c.getColumnIndex("name")));
            text.setText(c.getString(c.getColumnIndex("text")));
            name_artist.setText(c.getString(c.getColumnIndex("Name")));}
    }
    catch (Exception ex){}
    }

    public String editSong(SQLiteDatabase bd, Cursor cursor, int id, String artist, String name, String text) {
        try{
            if_is=false;
            cursor= bd.rawQuery("select Name from artist;", null);
            if(cursor.moveToFirst())
            {
                while (!cursor.isAfterLast())
                {
                    if(name.equals(cursor.getString(cursor.getColumnIndex("Name")))) {
                        if_is=true;
                        break;
                    }
                    cursor.moveToNext();
                }
            }
            if(if_is==false)
                bd.execSQL("insert into artist (Name) values ('" +name+ "');");
            cursor = bd.rawQuery("select * from artist where Name like'" + artist + "'", null);
            if(cursor.moveToFirst())
            {
                while (!cursor.isAfterLast())
                {
                    if(cursor.getString(cursor.getColumnIndex("Name")).equals(artist))
                    { id_artist=cursor.getString(cursor.getColumnIndex("_id"));break;}
                    cursor.moveToNext();
                }
            }
            getIdSongs(cursor,bd);
            for (int i=0;i<id_songs.length;i++)
                if(i==id){
                    id=Integer.parseInt(id_songs[i]);
                    break;}
            bd.execSQL("update Song set artist=" + id_artist + ",name='" + name +"',text='" + text + "'where _id="+id+";");
            int y=0;
        }
        catch (Exception ex){return ex.getMessage().toString();};
        return "Успішно Збережено!";
    }
}
