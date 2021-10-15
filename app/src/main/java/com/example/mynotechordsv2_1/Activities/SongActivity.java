package com.example.mynotechordsv2_1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.mynotechordsv2_1.R;
import com.example.mynotechordsv2_1.classes.Song;

import java.util.Timer;
import java.util.TimerTask;

public class SongActivity extends AppCompatActivity {
    private TextView text, name, artist;
    private Bundle bundle;
    private SeekBar seekBar;
    Cursor cursor;
    private Button ton_up, ton_down;
    float size=0;
    Timer timer;
    TimerTask timerTask;
    public ObjectAnimator objectAnimator ;
    ScrollView scrollView;
    SQLiteDatabase db;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        song=new Song(this);
        db=song.getReadableDatabase();
        seekBar=(SeekBar)findViewById(R.id.speed);
        text=(TextView)findViewById(R.id.text);
        name=(TextView)findViewById(R.id.name);
        size= 20;
        scrollView=(ScrollView)findViewById(R.id.scroll);
        text.setTextSize(size);
        artist=(TextView)findViewById(R.id.artist);
        bundle=getIntent().getExtras();
        ton_down=(Button)findViewById(R.id.min_ton);
        ton_up=(Button)findViewById(R.id.max_ton);
        openSong(Integer.parseInt(bundle.get("value").toString()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                timer=new Timer();
                timerTask=new TimerTask() {
                    @Override
                    public void run() {
                        scrollView.setScrollY(scrollView.getScrollY()+seekBar.getProgress());
                    }
                };
                timer.scheduleAtFixedRate(timerTask,2000,500);
            }
        });

    }

    public  void tonUp(View v)
    {
        text.setText(song.getTextEditTone(text.getText().toString().toCharArray(),ton_up.getText().toString()));
    }
    public  void tonDown(View v)
    {
        text.setText(song.getTextEditTone(text.getText().toString().toCharArray(),ton_down.getText().toString()));
    }
    public void  minFont(View v)
    {
        size=size-1;
        text.setTextSize(size);
    }
    public void  maxFont(View v)
    {
        size=size+1;
        text.setTextSize(size);
    }
    public void openSong(int value)
    {
        song.getSong(value, db,cursor,name,artist,text);
    }


}
