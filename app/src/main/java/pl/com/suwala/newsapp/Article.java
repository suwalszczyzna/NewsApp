package pl.com.suwala.newsapp;

import android.graphics.drawable.Drawable;

public class Article {

    private String id;
    private String title;
    private String author;
    private String date;
    private Drawable imgThumbnail;
    private String url;
    private String sectionName;


    public Article(String id, String title, String author, String date, Drawable imgThumbnail, String url, String sectionName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.imgThumbnail = imgThumbnail;
        this.url = url;
        this.sectionName = sectionName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public Drawable getImgThumbnail() {
        return imgThumbnail;
    }

    public String getSectionName() {
        return sectionName;
    }

}
