package com.quypn.truyenngan.PNQ18101997;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.AdapterStoryExpand.Profile;
import com.SQliteDatabase.SQliteFavorite;
import com.SQliteDatabase.SQliteHistory;
import com.example.quypn.truyenngan2.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.rey.material.widget.Slider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ReaderActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView txt_content;
    private Toolbar toobarr;
    private TextView txt_title;
    private ProgressBar progress;
    private AdView adView;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private RelativeLayout relative;
    private String share_content = "";


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorSTTbar));
        }


        toobarr = (Toolbar) findViewById(R.id.toobarr);
        setSupportActionBar(toobarr);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        relative = (RelativeLayout) findViewById(R.id.relative);
        //AD
        RelativeLayout adViewContainer = (RelativeLayout) findViewById(R.id.adViewContainer);
        adView = new AdView(this, "801258106690060_812529675562903", AdSize.BANNER_320_50);
        adViewContainer.addView(adView);
        adView.loadAd();

        txt_content = (WebView) findViewById(R.id.txt_content);
        txt_content.getSettings().setJavaScriptEnabled(true);


        txt_title = (TextView) findViewById(R.id.txt_title);
        progress = (ProgressBar) findViewById(R.id.progress);

        Config.profile = (Profile) getIntent().getExtras().getSerializable("profile");


        txt_title.setText(Config.profile.getTitle());
        getSupportActionBar().setTitle(Config.profile.getTitle());
        if (Config.modeReader) ModeNight();
        else ModeLight();


        /*
            Đọc offline
         */
        if (Config.isNetworkConected(this)) {
            new LoadContent().execute(Config.profile.getUrl());
        } else {
            txt_content.loadDataWithBaseURL(null, Config.profile.getContent(), "text/html", "UTF-8", null);

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option2_menu, menu);

        inflater.inflate(R.menu.bookmark_menu, menu);
        MenuItem favorite_MN = menu.findItem(R.id.menu_favorite);

        if (Config.profile.isFavoite())
            favorite_MN.setIcon(getResources().getDrawable(R.drawable.ic_favorited));
        else favorite_MN.setIcon(getResources().getDrawable(R.drawable.ic_favorite));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_share:
                share_content();
                break;
            case R.id.menu_share_facebook:
                share_content_fb();
                break;
            case R.id.menu_favorite:
                Config.profile.setFavoite(!Config.profile.isFavoite());
                if (Config.profile.isFavoite()) {
                    Toast.makeText(this, "đã thêm truyện vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                    item.setIcon(getResources().getDrawable(R.drawable.ic_favorited));
                    SQliteFavorite sQliteFavorite = new SQliteFavorite(this);
                    sQliteFavorite.insertStory(Config.profile);
                } else {
                    Toast.makeText(this, "đã xóa truyện khỏi yêu thích", Toast.LENGTH_SHORT).show();
                    SQliteFavorite sQliteFavorite = new SQliteFavorite(this);
                    sQliteFavorite.deleteStory(Config.profile.getIdf());


                    item.setIcon(getResources().getDrawable(R.drawable.ic_favorite));

                }
                break;
            case android.R.id.home: {
                finish();
            }
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_sang:
                ModeLight();
                Config.modeReader = false;
                break;

            case R.id.btn_toi:
                ModeNight();
                Config.modeReader = true;
                break;
        }

    }

    class LoadContent extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Document document = Jsoup.connect(params[0]).userAgent(Config.UserAgent).timeout(60000).get();
                Elements elements = document.select("div.maincontent");
                Log.w("content_xxx", elements.toString());

                Config.content = elements.toString();

                String config = "";
                config += "<h1 align=\"center\" >" + Config.profile.getTitle() + "</h1>";
                config += elements.toString().replaceAll("<img", "<img style=\"display: none;");
                config += "<p style=\"text-align: justify;\"><strong>#Tobi</strong></p>";
                Config.content = "<html>\n" +
                        "<head>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width\">\n" +
                        "  <title>JS Bin</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "   <p style=\"text-align: justify;\">" + config + "</p>\n" +
                        "</body>\n" +
                        "</html>";


                for (int i = 0; i < elements.size(); i++) {
                    Element element = elements.get(i);
                    String content = element.text();
                    share_content += "\n" + content;


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            txt_content.loadDataWithBaseURL(null, Config.content, "text/html", "UTF-8", null);
            Config.profile.setContent(Config.content);

            progress.setVisibility(View.GONE);
            SQliteHistory sQliteHistory = new SQliteHistory(ReaderActivity.this);
            sQliteHistory.insertStory(Config.profile);


        }
    }

    private void share_content_fb() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(Uri.
                    parse(Config.linkGGPlay)).
                    setQuote(txt_title.getText().toString() + ": \n   " + share_content).build();

            shareDialog.show(content);
        }
    }

    private void share_content() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, txt_title.getText().toString() + "\n " + share_content + "\n" + Config.linkGGPlay);
        startActivity(Intent.createChooser(intent, "Chia sẻ với: "));
    }

    private void ModeNight() {

        /*txt_content.setTextColor(getResources().getColor(R.color.whrite));
        relative.setBackgroundResource(R.color.black);
        txt_title.setTextColor(getResources().getColor(R.color.whrite));
        toobarr.setTitleTextColor(getResources().getColor(R.color.whrite));*/
    }

    private void ModeLight() {

       /* txt_content.setTextColor(getResources().getColor(R.color.black));
        relative.setBackgroundResource(R.drawable.bg3);
        txt_title.setTextColor(getResources().getColor(R.color.black));
        toobarr.setTitleTextColor(getResources().getColor(R.color.black));*/
    }

    private void Mn_Setting() {
        Dialog dialog = new Dialog(this);
        Slider slider;
        RadioButton btn_sang;
        RadioButton btn_toi;


        dialog.setContentView(R.layout.dialog_setting);
        btn_sang = (RadioButton) dialog.findViewById(R.id.btn_sang);
        btn_toi = (RadioButton) dialog.findViewById(R.id.btn_toi);
        slider = (Slider) dialog.findViewById(R.id.set_text_size);

        if (!Config.modeReader) btn_sang.setChecked(true);
        else btn_toi.setChecked(true);

        btn_sang.setOnClickListener(this);
        btn_toi.setOnClickListener(this);


        slider.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
            @Override
            public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {

            }
        });
        dialog.show();
    }


}
