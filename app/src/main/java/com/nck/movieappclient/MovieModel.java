package com.nck.movieappclient;

public class MovieModel {

    public String smallLinkTwo;
    public String bigLinkTwo;

    public String movieRating;
    public String coverImage;
    public String smallLink;
    public String bigLink;

    public String movieName;
    public String movieImage;
    public String movieVideo;
    public String movieCategory;
    public int movieCount;
    public String createdArt;

    public String getSmallLinkTwo() {
        return smallLinkTwo;
    }

    public void setSmallLinkTwo(String smallLinkTwo) {
        this.smallLinkTwo = smallLinkTwo;
    }

    public String getBigLinkTwo() {
        return bigLinkTwo;
    }

    public void setBigLinkTwo(String bigLinkTwo) {
        this.bigLinkTwo = bigLinkTwo;
    }

    public MovieModel(String smallLinkTwo, String bigLinkTwo, String movieRating, String coverImage, String smallLink, String bigLink, String movieName, String movieImage, String movieVideo, String movieCategory, int movieCount, String createdArt) {
        this.smallLinkTwo = smallLinkTwo;
        this.bigLinkTwo = bigLinkTwo;
        this.movieRating = movieRating;
        this.coverImage = coverImage;
        this.smallLink = smallLink;
        this.bigLink = bigLink;
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieVideo = movieVideo;
        this.movieCategory = movieCategory;
        this.movieCount = movieCount;
        this.createdArt = createdArt;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getSmallLink() {
        return smallLink;
    }

    public void setSmallLink(String smallLink) {
        this.smallLink = smallLink;
    }

    public String getBigLink() {
        return bigLink;
    }

    public void setBigLink(String bigLink) {
        this.bigLink = bigLink;
    }

    public MovieModel(String movieRating, String coverImage, String smallLink, String bigLink, String movieName, String movieImage, String movieVideo, String movieCategory, int movieCount, String createdArt) {
        this.movieRating = movieRating;
        this.coverImage = coverImage;
        this.smallLink = smallLink;
        this.bigLink = bigLink;
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieVideo = movieVideo;
        this.movieCategory = movieCategory;
        this.movieCount = movieCount;
        this.createdArt = createdArt;
    }

    public MovieModel(String movieName, String movieImage, String movieVideo, String movieCategory, int movieCount, String createdArt) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieVideo = movieVideo;
        this.movieCategory = movieCategory;
        this.movieCount = movieCount;
        this.createdArt = createdArt;
    }

    public MovieModel(String movieName, String movieImage, String movieVideo, String movieCategory, int movieCount) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieVideo = movieVideo;
        this.movieCategory = movieCategory;
        this.movieCount = movieCount;
    }

    public MovieModel(String movieName, String movieImage, String movieVideo, String movieCategory) {
        this.movieName = movieName;
        this.movieImage = movieImage;
        this.movieVideo = movieVideo;
        this.movieCategory = movieCategory;
    }

    public MovieModel() {
    }

    public String getMovieName() {
        return movieName;
    }

    public int getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(int movieCount) {
        this.movieCount = movieCount;
    }

    public String getCreatedArt() {
        return createdArt;
    }

    public void setCreatedArt(String createdArt) {
        this.createdArt = createdArt;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieVideo() {
        return movieVideo;
    }

    public void setMovieVideo(String movieVideo) {
        this.movieVideo = movieVideo;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }
}
