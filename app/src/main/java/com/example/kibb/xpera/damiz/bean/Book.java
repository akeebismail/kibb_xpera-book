package com.example.kibb.xpera.damiz.bean;

import java.io.Serializable;

/**
 * Created by KIBB on 8/30/2017.
 */

public class Book implements Serializable{
    private String title;
    private String sub_title;
    private String alt_title;
    private String[] author;
    private String publisher;
    private String catalog;
    private String id;
    private String summary;
    private String image;
    private String author_intro;
    private String price;
    private String pubdate;
    private Images images;
    private String pages;

    public Book(){}
    public Book(String title){
        this.title= title;
    }
    public class Images implements Serializable{
        private String dSmall;
        private String dLarge;
        private String dMedium;

        public String getSmall(){return dSmall;}
        public void setSmall(String small){
            this.dSmall= small;
        }

        public String getLarge(){return dLarge;}
        public void setLarge(String large){
            this.dLarge= large;
        }
        public String getMedium(){return dMedium;}
        public void setMedium(String medium){this.dMedium= medium;}

    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String[] getAuthor() {
        return author;
    }

    public void setAuthor(String[] author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "[" + title + "]";
    }
}
