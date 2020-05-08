package com.nck.ychannelmovie;

public class EpisodeModel {

    public String episodeName;
    public String episodeVideo;
    public String episodeSeries;
    public String createdArt;

    public EpisodeModel(String episodeName, String episodeVideo, String episodeSeries, String createdArt) {
        this.episodeName = episodeName;
        this.episodeVideo = episodeVideo;
        this.episodeSeries = episodeSeries;
        this.createdArt = createdArt;
    }

    public EpisodeModel(String episodeName, String episodeVideo, String episodeSeries) {
        this.episodeName = episodeName;
        this.episodeVideo = episodeVideo;
        this.episodeSeries = episodeSeries;
    }

    public EpisodeModel() {
    }

    public String getCreatedArt() {
        return createdArt;
    }

    public void setCreatedArt(String createdArt) {
        this.createdArt = createdArt;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getEpisodeVideo() {
        return episodeVideo;
    }

    public void setEpisodeVideo(String episodeVideo) {
        this.episodeVideo = episodeVideo;
    }

    public String getEpisodeSeries() {
        return episodeSeries;
    }

    public void setEpisodeSeries(String episodeSeries) {
        this.episodeSeries = episodeSeries;
    }
}
