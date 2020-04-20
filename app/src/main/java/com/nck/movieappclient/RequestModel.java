package com.nck.movieappclient;

public class  RequestModel {

    public String name;
    public String imagelink;
    public String createdArt;

    public String getCreatedArt() {
        return createdArt;
    }

    public void setCreatedArt(String createdArt) {
        this.createdArt = createdArt;
    }

    public RequestModel(String name, String imagelink, String createdArt) {
        this.name = name;
        this.imagelink = imagelink;
        this.createdArt = createdArt;
    }

    public RequestModel(String name, String imagelink) {
        this.name = name;
        this.imagelink = imagelink;
    }

    public RequestModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }
}
