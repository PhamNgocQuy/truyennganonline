package com.quypn.truyenngan.PNQ18101997;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AdapterStoryExpand.Profile;
import com.SQliteDatabase.SQliteHistory;
import com.example.quypn.truyenngan2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LichsuFragment extends Fragment implements IRefesh {

    private RclAdapter rclAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Profile> lsData;

    public LichsuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lichsu, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyView);

        SQliteHistory sQliteHistory = new SQliteHistory(getContext());
        lsData = sQliteHistory.getListStory();

        rclAdapter = new RclAdapter(lsData, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rclAdapter);


    }

    @Override
    public void refresh() {
        lsData = new ArrayList<>();
        rclAdapter = new RclAdapter(lsData, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rclAdapter);
    }

}
