package com.nck.movieappclient;

public class SeriesModel {

    String seriesName,seriesImage,seriesCategory,createdArt,seriesReview,seriesRating,seriesLink;
    int seriesCount;

    public String getSeriesReview() {
        return seriesReview;
    }

    public void setSeriesReview(String seriesReview) {
        this.seriesReview = seriesReview;
    }

    public String getSeriesRating() {
        return seriesRating;
    }

    public void setSeriesRating(String seriesRating) {
        this.seriesRating = seriesRating;
    }

    public String getSeriesLink() {
        return seriesLink;
    }

    public void setSeriesLink(String seriesLink) {
        this.seriesLink = seriesLink;
    }

    public SeriesModel(String seriesName, String seriesImage, String seriesCategory, String createdArt, String seriesReview, String seriesRating, String seriesLink, int seriesCount) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seriesCategory = seriesCategory;
        this.createdArt = createdArt;
        this.seriesReview = seriesReview;
        this.seriesRating = seriesRating;
        this.seriesLink = seriesLink;
        this.seriesCount = seriesCount;
    }

    public SeriesModel(String seriesName, String seriesImage, String seriesCategory, String createdArt, int seriesCount) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seriesCategory = seriesCategory;
        this.createdArt = createdArt;
        this.seriesCount = seriesCount;
    }

    public SeriesModel(String seriesName, String seriesImage, String seriesCategory, int seriesCount) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seriesCategory = seriesCategory;
        this.seriesCount = seriesCount;
    }

    public int getSeriesCount() {
        return seriesCount;
    }

    public void setSeriesCount(int seriesCount) {
        this.seriesCount = seriesCount;
    }

    public SeriesModel(String seriesName, String seriesImage, String seriesCategory) {
        this.seriesName = seriesName;
        this.seriesImage = seriesImage;
        this.seriesCategory = seriesCategory;
    }

    public SeriesModel() {
    }

    public String getCreatedArt() {
        return createdArt;
    }

    public void setCreatedArt(String createdArt) {
        this.createdArt = createdArt;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesImage() {
        return seriesImage;
    }

    public void setSeriesImage(String seriesImage) {
        this.seriesImage = seriesImage;
    }

    public String getSeriesCategory() {
        return seriesCategory;
    }

    public void setSeriesCategory(String seriesCategory) {
        this.seriesCategory = seriesCategory;
    }
}
