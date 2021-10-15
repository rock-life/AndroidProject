package com.example.mynotechordsv2_1.category;

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
import com.example.mynotechordsv2_1.classes.Category;
import com.example.mynotechordsv2_1.classes.CategoryAdapter;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private CategoryViewModel mViewModel;
    ArrayList<Category> states=new ArrayList<Category>();

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        recyclerView=(RecyclerView)getActivity().findViewById(R.id.list_category);
        setInitialData();
        CategoryAdapter.OnStateClickListener stateClickListener=new CategoryAdapter.OnStateClickListener() {
            @Override
            public void OnSteteClick(Category category, int position) {
                ////обробка.............state.....................................
            }
        };
        CategoryAdapter adapter=new CategoryAdapter(getActivity(),states,stateClickListener);
        recyclerView.setAdapter(adapter);
        return inflater.inflate(R.layout.category_fragment, container, false);
    }

    private void setInitialData() {
        //Заповнити адаптер
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        // TODO: Use the ViewModel
    }

}