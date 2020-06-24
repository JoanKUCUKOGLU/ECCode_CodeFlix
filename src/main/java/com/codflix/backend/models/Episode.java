package com.codflix.backend.models;

public class Episode {
    private int id;
    private int mediaId;
    private int saisonIndex;
    private int episodeIndex;
    private String name;
    private int duration;
    private String summary;
    private String url;

    public Episode() {
    }

    public Episode(int id, int mediaId, int saisonIndex, int episodeIndex, String name, int duration, String summary, String url) {
        this.id = id;
        this.mediaId = mediaId;
        this.saisonIndex = saisonIndex;
        this.episodeIndex = episodeIndex;
        this.name = name;
        this.duration = duration;
        this.summary = summary;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getSaisonIndex() {
        return saisonIndex;
    }

    public void setSaisonIndex(int saisonIndex) {
        this.saisonIndex = saisonIndex;
    }

    public int getEpisodeIndex() {
        return episodeIndex;
    }

    public void setEpisodeIndex(int episodeIndex) {
        this.episodeIndex = episodeIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDurationString() {
        String durationString = "";
        durationString += (int)(duration/1440) > 0 ? (int)(duration/1440) + "d" : "";
        durationString += (int)(duration/60) > 0 ? (int)(duration/60) + "h" : "";
        durationString += duration%60 + (duration < 60 ? "m" : "");
        return durationString;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
