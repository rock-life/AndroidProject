package com.example.mynotechordsv2_1.artist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynotechordsv2_1.R;
import com.example.mynotechordsv2_1.classes.Artist;
import com.example.mynotechordsv2_1.classes.ArtistAdapter;
import com.example.mynotechordsv2_1.classes.Category;
import com.example.mynotechordsv2_1.classes.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ArtistFragment extends Fragment {

    private ArtistViewModel mViewModel;
    private RecyclerView ListArtist;
    private ArtistAdapter artistAdapter;
    private ArrayList<Artist> ListArtistArray=new ArrayList<Artist>();

    public static ArtistFragment newInstance() {
        return new ArtistFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.artist_fragment, container, false);
       ListArtist=(RecyclerView)view.findViewById(R.id.List_artist);
        ArtistAdapter.onArtistClickListener ClickListener=new ArtistAdapter.onArtistClickListener() {
            @Override
            public void OnArtistClick(Artist artist, int position) {

            }
        };
        ArtistAdapter artistAdapter=new ArtistAdapter(getActivity(),ListArtistArray,ClickListener);
        ListArtist.setAdapter(artistAdapter);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ArtistViewModel.class);
        // TODO: Use the ViewModel
    }

}