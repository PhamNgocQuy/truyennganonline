package com.AdapterStoryExpand;

import java.io.Serializable;

/**
 * Created by QuyPN on 4/9/2017.
 */

public class Profile_Type implements Serializable{

    private String url;
    private String title;
    private String stt;

    public Profile_Type(String url, String title,String stt) {
        this.url = url;
        this.title = title;
        this.stt = stt;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
