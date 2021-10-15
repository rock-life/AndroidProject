package com.example.mynotechordsv2_1.ui.songs;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotechordsv2_1.classes.Artist;
import com.example.mynotechordsv2_1.R;
import com.example.mynotechordsv2_1.classes.Song;

public class SongsFragment extends Fragment {
    int id_song;
    Artist artist;
    Song song;
    TextView t;Button bt;
    SQLiteDatabase bd;
    Cursor cursor;
    ListView listView;
    private SongsViewModel mViewModel;

    public static SongsFragment newInstance() {
        return new SongsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.songs_fragment, container, false);
        t=view.findViewById(R.id.text);
        bt=(Button)view.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    NavController navController= Navigation.findNavController(view);
                    navController.navigate(R.id.nav_song);


                } catch (Exception ex) {
                    Toast.makeText(getContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                    // Potentially direct the user to the Market with a Dialog

                }
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            t.setText(data.getData().toString());
            MediaPlayer mediaPlayer=MediaPlayer.create(getActivity(),data.getData());
            mediaPlayer.start();
        }catch (Exception e)
        {
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
        t.setText(savedInstanceState.get("key").toString());

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SongsViewModel.class);
        // TODO: Use the ViewModel
    }

}


 /*
                    FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.nav_songs, fragment);
                    fragmentTransaction.commit();
                    Intent intent1=new Intent();
                    intent1.setType("audio/*");
                    intent1.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent1, "Select a File to Upload"),1);*/
