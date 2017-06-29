package com.example.quypn.truyenngan2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.AdapterStoryExpand.AdapterStory;
import com.AdapterStoryExpand.ClassifyStory;
import com.AdapterStoryExpand.Profile_Type;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class YeuFragment extends Fragment {

    /*private RclAdapterType rclAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Profile_Type> lsData = new ArrayList<>();*/


    private RecyclerView recyclerView;
    private AdapterStory adapterStory;
    private ArrayList<ClassifyStory> listClassify;

    public YeuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yeu, null);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyView);

        listClassify = new ArrayList<>();


        List<Profile_Type> tonghopData = new ArrayList<>();
        tonghopData.add(new Profile_Type("http://www.truyenngan.com.vn/tags/trang-ha/", "Trang hạ", "1"));
        tonghopData.add(new Profile_Type("http://www.truyenngan.com.vn/tags/tinh-yeu-tan-vo/", "Tình yêu tan vỡ", "2"));
        tonghopData.add(new Profile_Type("http://www.truyenngan.com.vn/tags/chuyen-sau-khi-ket-hon/", "Chuyện sau khi kết hôn", "3"));
        listClassify.add(new ClassifyStory("Những chuyện được yêu thích", tonghopData));
        /*
            Short Story
         */
        List<Profile_Type> lsData = new ArrayList<>();
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-yeu/", "Yêu", "1"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-ban/", "Bạn", "2"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-song/", "Sống", "3"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-gia-dinh/", "Gia đình", "4"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-tan-man/", "Tản mạn", "5"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-kinh-di/", "Kinh dị", "6"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/ngu-ngon/", "Ngụ Ngôn", "7"));
        listClassify.add(new ClassifyStory("Truyện Ngắn", lsData));

        /*
            Blog
         */


        List<Profile_Type> blogData = new ArrayList<>();
        blogData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-blog/trai-nghiem-song/", "Trải nghiệm sống", "1"));
        blogData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-blog/trai-nghiem-yeu/", "Trải nghiệm yêu", "2"));
        listClassify.add(new ClassifyStory("Truyện Blog", blogData));

        List<Profile_Type> tamsuData = new ArrayList<>();
        tamsuData.add(new Profile_Type("http://www.truyenngan.com.vn/tam-su/", "Tâm sự", "1"));
        tamsuData.add(new Profile_Type("http://www.truyenngan.com.vn/tags/nhung-cau-chuyen-dang-suy-ngam/", "Chuyện đáng suy ngẫm", "2"));
        listClassify.add(new ClassifyStory("Tâm sự", tamsuData));


        adapterStory = new AdapterStory(listClassify, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterStory);

        adapterStory.setOnGroupExpandCollapseListener(new GroupExpandCollapseListener() {
            @Override
            public void onGroupExpanded(ExpandableGroup group) {

            }

            @Override
            public void onGroupCollapsed(ExpandableGroup group) {

            }
        });


        /*recyclerView = (RecyclerView) getView().findViewById(R.id.recyView);
        lsData = new ArrayList<>();
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-yeu/","Yêu","1"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-ban/","Bạn","2"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-song/","Sống","3"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-gia-dinh/","Gia đình","4"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-tan-man/","Tản mạn","5"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/truyen-ngan-kinh-di/","Kinh dị","6"));
        lsData.add(new Profile_Type("http://www.truyenngan.com.vn/truyen-ngan/ngu-ngon/","Ngụ Ngôn","7"));

        rclAdapter = new RclAdapterType(lsData, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rclAdapter);*/


    }

}
