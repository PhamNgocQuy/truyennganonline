package com.quypn.truyenngan.PNQ18101997;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AdapterStoryExpand.Profile;
import com.SQliteDatabase.SQliteFavorite;
import com.example.quypn.truyenngan2.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class YeuthichFragment extends Fragment {

    private RclAdapter rclAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Profile> lsData;
    private SwipeRefreshLayout swipeRefresh;

    public YeuthichFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yeuthich, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefresh = (SwipeRefreshLayout) getView().findViewById(R.id.swipeRefresh);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyView);

        SQliteFavorite sQliteFavorite = new SQliteFavorite(getContext());
        lsData = sQliteFavorite.getListStory();

        rclAdapter = new RclAdapter(lsData, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rclAdapter);


        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SQliteFavorite sQliteFavorite = new SQliteFavorite(getContext());
                lsData = sQliteFavorite.getListStory();

                rclAdapter = new RclAdapter(lsData, getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(rclAdapter);
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }
}
