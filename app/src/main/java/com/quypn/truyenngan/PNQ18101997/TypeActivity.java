package com.quypn.truyenngan.PNQ18101997;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.AdapterStoryExpand.GirdViewAdapter;
import com.AdapterStoryExpand.Profile;
import com.AdapterStoryExpand.Profile_Type;
import com.example.quypn.truyenngan2.R;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TypeActivity extends AppCompatActivity {

    private AdView adView;
    private ProgressBar progressBar;
    /*private RecyclerView recyclerView;
    private RclAdapter adapter;*/

    private ArrayList<Profile> lsData = new ArrayList<>();
    private Toolbar toolbar;
    private Boolean Loading = true;
    private int totalItemCount;
    /*
     private int lastVisibleItem;
    */
    private GirdViewAdapter adapter;
    private GridView girdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorSTTbar));
        }
        RelativeLayout adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);
        adView = new AdView(this, "801258106690060_801262880022916", AdSize.BANNER_320_50);
        adViewContainer.addView(adView);
        adView.loadAd();
        init();

        Config.profile_type = (Profile_Type) getIntent().getExtras().get("type");
        toolbar.setTitle(Config.profile_type.getTitle());
        toolbar.setTitleTextColor(getResources().getColor(R.color.whrite));
        toolbar.setDrawingCacheEnabled(true);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        adapter = new GirdViewAdapter(lsData, TypeActivity.this, getLayoutInflater());
        girdView.setAdapter(adapter);
        new LoadData().execute(Config.profile_type.getUrl());

        girdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Log.w("click", "click");
                Intent intent = new Intent(TypeActivity.this, ReaderActivity.class);
                intent.putExtra("profile", adapter.getItem(position));
                startActivity(intent);
            }
        });

        girdView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (girdView.getLastVisiblePosition() + 1 >= totalItemCount) {
                    if (Loading && !Config.page_error) {
                        Config.PageNumber += 1;
                        new LoadData().execute(Config.profile_type.getUrl() + Config.PageNumber);

                    }
                }
            }
        });



       /* adapter = new RclAdapter(lsData, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    lastVisibleItem = manager.findLastVisibleItemPosition();
                    totalItemCount = manager.getItemCount();
                    if (totalItemCount - 1 <= lastVisibleItem && Loading == true) {
                        Config.PageNumber += 1;
                        new LoadData().execute(Config.profile_type.getUrl() + Config.PageNumber);
                    }
                }

            }
        });*/

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toobarr);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        girdView = (GridView) findViewById(R.id.girdview_mdl);
        //recyclerView = (RecyclerView) findViewById(R.id.recyView);
    }


    class LoadData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            totalItemCount = lsData.size();
            Loading = false;
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Document document = Jsoup.connect(params[0]).userAgent(Config.UserAgent).timeout(60000).get();
                Elements elements = document.select("div.box a");

                for (int i = 0; i < elements.size(); i++) {
                    Element element = elements.get(i);
                    Elements e = element.select("img");
                    String title = element.attr("title");
                    String url = element.attr("href");
                    String src = e.attr("src");
                    String url_store = "http://192.168.0.101:3000/insert/1/1/1";
                    postAnRequest(url_store);

                    if (!Config.profile_type.getTitle().equalsIgnoreCase("Ngụ Ngôn")) {

                        if (src != "" && title != "") {
                            lsData.add(new Profile(url, title, src, "Tản mạn"));
                        }
                    } else {
                        if (!src.equalsIgnoreCase("") && !title.equalsIgnoreCase("Mèo và chuột già")) {
                            lsData.add(new Profile(url, title, src, "Tản mạn"));
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                Config.page_error = true;

            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (totalItemCount == lsData.size()) {
                Config.page_error = true;
            }
            Loading = true;
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Config.PageNumber = 0;
            Config.page_error = false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Config.PageNumber = 0;
                Config.page_error = false;
                super.onBackPressed();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void postAnRequest(String link) throws IOException {
        try {
            URL url = new URL(link);
            Log.w("connect", "connect");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }
}
