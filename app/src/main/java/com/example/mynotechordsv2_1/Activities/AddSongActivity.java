package com.example.mynotechordsv2_1.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotechordsv2_1.classes.Artist;
import com.example.mynotechordsv2_1.R;
import com.example.mynotechordsv2_1.classes.Song;

public class AddSongActivity extends AppCompatActivity {

    Artist artist;
    Song song;
    SQLiteDatabase bd;
    Cursor cursor;
    EditText name_artist;
    EditText name_song;
    TextView id;
    EditText text;
    Bundle bundle;
    Button add;
    Cursor context;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artist=new Artist(this);
        song=new Song(this);
        bd = getBaseContext().openOrCreateDatabase("artist.db",MODE_PRIVATE,null);
        artist.onCreate(bd);
        song.onCreate(bd);
        setContentView(R.layout.activity_add_song);
        add=(Button)findViewById(R.id.add);
        add.setText("Додати");
        name_artist=(EditText)findViewById(R.id.Artist);
        name_song=(EditText)findViewById(R.id.NameSong);
        id=(TextView)findViewById(R.id.id_song);
        text=(EditText)findViewById(R.id.TextSong);
        try {
            bundle=getIntent().getExtras();
            id.setText(bundle.get("value").toString());
            song.getToEditSong(context,bd,Integer.parseInt(id.getText().toString()),name_artist,name_song,text);
            add.setText("Редагувати");

        }
        catch (Exception ex){};

    }
    public void addclick(View viev)
    {
        if(!name_artist.getText().toString().equals("")){
            try {
                name_artist.getText().toString();
                artist.add(bd, cursor, name_artist.getText().toString());
                if(!name_song.getText().toString().equals("")&& !text.getText().toString().equals(""))
                {
                    if(add.getText().toString().equals("Додати"))
                        toast=Toast.makeText(this, song.add(bd, cursor, name_artist.getText().toString(),name_song.getText().toString(),text.getText().toString()),Toast.LENGTH_LONG);
                    else
                        toast=Toast.makeText(this,song.editSong(bd, cursor,Integer.parseInt(id.getText().toString()), name_artist.getText().toString(),name_song.getText().toString(),text.getText().toString()),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            catch (Exception e){toast = Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG);
                toast.show();}
        }

    }
    private void plus_click(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setPositiveButton("Прийняти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog alertDialog=(AlertDialog) dialog ;
            }
        });
        ConstraintLayout l=(ConstraintLayout)findViewById(R.id.dialog);
        builder.setView(l);
        builder.show();

    }
}