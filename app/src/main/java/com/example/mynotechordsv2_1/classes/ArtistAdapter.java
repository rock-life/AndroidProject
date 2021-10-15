package com.example.mynotechordsv2_1.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotechordsv2_1.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

   public interface  onArtistClickListener{
        public void OnArtistClick(Artist artist, int position);
    }

    private onArtistClickListener onClickListener;
    private LayoutInflater inflater;
    private List<Artist> ListArtist;

    public ArtistAdapter(Context context, List<Artist> ListArtist, onArtistClickListener onClickListener){
    inflater=LayoutInflater.from(context);
    this.ListArtist=ListArtist;
    this.onClickListener=onClickListener;
    }


    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.list_item_artist,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ArtistAdapter.ViewHolder holder, int position) {
        Artist artist= ListArtist.get(position);
        holder.name.setText(artist.getName());
        holder.valueSongs.setText(artist.getNumberOfSongs()+" - пісень");
        holder.isChecked.setChecked(artist.IsSelected);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnArtistClick(artist, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListArtist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, valueSongs;
        final CheckBox isChecked;
         public ViewHolder(View view){
             super(view);
             name=(TextView)view.findViewById(R.id.list_item_artist);
             valueSongs=(TextView)view.findViewById(R.id.list_item_songs_artist);
             isChecked=(CheckBox)view.findViewById(R.id.is_checked_artist);
         }
    }
}
