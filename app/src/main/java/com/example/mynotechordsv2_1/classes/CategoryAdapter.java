package com.example.mynotechordsv2_1.classes;


import com.example.mynotechordsv2_1.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
   public interface OnStateClickListener  {
        void OnSteteClick(Category state, int position);
    }
    private final OnStateClickListener onClickListener;
    private final LayoutInflater inflater;
    private final List<Category> states;

    public CategoryAdapter(Context context, ArrayList<Category> states, OnStateClickListener onClickListener){
        this.onClickListener=onClickListener;
        this.inflater=LayoutInflater.from(context);
        this.states=states;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        Category state=states.get(position);
        holder.nameView.setText(state.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.OnSteteClick(state, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static  class ViewHolder extends RecyclerView.ViewHolder{
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            nameView=(TextView)view.findViewById(R.id.nameCategory);
        }
    }
}
