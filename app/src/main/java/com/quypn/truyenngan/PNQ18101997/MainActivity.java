package com.quypn.truyenngan.PNQ18101997;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.AdapterStoryExpand.Profile;
import com.SQliteDatabase.SQLiteDataController;
import com.SQliteDatabase.SQliteHistory;
import com.example.quypn.truyenngan2.R;
import com.example.quypn.truyenngan2.YeuFragment;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbarr;
    private ViewPager viewPagee;
    private TabLayout tabLayoutt;
    private FragmentAdapter adapter;
    private AdView adView;
    private IRefesh historyRefesh;
    private LichsuFragment lichsuFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorSTTbar));
        }


        SQLiteDataController sql = new SQLiteDataController(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        toolbarr = (Toolbar) findViewById(R.id.toobarr);
        toolbarr.setTitle("Truyện ngắn");
        toolbarr.setTitleTextColor(getResources().getColor(R.color.whrite));

        setSupportActionBar(toolbarr);
        RelativeLayout adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);
        adView = new AdView(this, "801258106690060_801262880022916", AdSize.BANNER_320_50);
        adViewContainer.addView(adView);
        adView.loadAd();


        viewPagee = (ViewPager) findViewById(R.id.view_pagee);
        tabLayoutt = (TabLayout) findViewById(R.id.tab_layoutt);

        lichsuFragment = new LichsuFragment();
        historyRefesh = lichsuFragment;


        Fragment[] screens = {new YeuFragment(), new YeuthichFragment(), lichsuFragment};


        FragmentManager manager = getSupportFragmentManager();
        adapter = new FragmentAdapter(manager, screens, MainActivity.this);
        viewPagee.setAdapter(adapter);

        tabLayoutt.setupWithViewPager(viewPagee);
        viewPagee.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutt));
        tabLayoutt.setTabsFromPagerAdapter(adapter);

        tabLayoutt.getTabAt(0).setIcon(R.mipmap.ic_classify_unselect);
        tabLayoutt.getTabAt(1).setIcon(R.mipmap.ic_favorite_unselect);
        tabLayoutt.getTabAt(2).setIcon(R.mipmap.ic_history_unselect);


        tabLayoutt.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setSelectedTapIcon(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setUnSelectedTapIcon(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        if (!Config.isNetworkConected(this)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.NoInternetConnected);
            builder.setMessage("Bạn vẫn có thể đọc truyện trong mục yêu thích và lịch sử");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.create().show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu_cleanHistory:
                XoaLichSu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void XoaLichSu() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SQliteHistory sQliteHistory = new SQliteHistory(MainActivity.this);
                ArrayList<Profile> profiles = sQliteHistory.getListStory();


                for (int i = 0; i < profiles.size(); i++) {
                    sQliteHistory.deleteStory(profiles.get(i).getId());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        historyRefesh.refresh();
                    }
                });
            }
        });
        thread.start();
        Toast.makeText(this, "Đã xóa lịch sử", Toast.LENGTH_SHORT).show();

    }

    public void setSelectedTapIcon(TabLayout.Tab tap) {
        switch (tap.getPosition()) {
            case 2:
                tap.setIcon(R.mipmap.ic_history);
                break;
            case 1:
                tap.setIcon(R.mipmap.ic_favorite);
                break;
            case 0:
                tap.setIcon(R.mipmap.ic_classify);
                break;
        }
    }

    public void setUnSelectedTapIcon(TabLayout.Tab tap) {
        switch (tap.getPosition()) {
            case 2:
                tap.setIcon(R.mipmap.ic_history_unselect);
                break;
            case 1:
                tap.setIcon(R.mipmap.ic_favorite_unselect);
                break;
            case 0:
                tap.setIcon(R.mipmap.ic_classify_unselect);
                break;
        }
    }

}
