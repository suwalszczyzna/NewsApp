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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Drawable getImgThumbnail() {
        return imgThumbnail;
    }

    public void setImgThumbnail(Drawable imgThumbnail) {
        this.imgThumbnail = imgThumbnail;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
