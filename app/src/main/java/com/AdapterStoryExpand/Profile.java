package com.AdapterStoryExpand;

import java.io.Serializable;

/**
 * Created by QuyPN on 3/23/2017.
 */

public class Profile  implements Serializable{

    private String url;
    private String title;
    private String urlImg;
    private String theloai;
    private String content;
    private boolean favoite;
    private int id;
    private int idf;

    public Profile() {

    }

    public Profile(String url, String title, String UrlImg, String theloai) {
        this.urlImg = UrlImg;
        this.url = url;
        this.title = title;
        this.theloai = theloai;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public boolean isFavoite() { return favoite; }

    public void setFavoite(boolean favoite) { this.favoite = favoite; }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
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

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}
