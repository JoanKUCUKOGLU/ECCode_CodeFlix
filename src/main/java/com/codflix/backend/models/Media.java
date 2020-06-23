package com.codflix.backend.models;

import java.util.Date;
import java.util.List;

public class Media {
    private int id;
    private List<String> genreList;
    private String title;
    private String type;
    private String status;
    private Date releaseDate;
    private String summary;
    private Integer duration; // in minutes
    private String imgUrl;
    private String trailerUrl;

    public Media(int id, List<String> genreList, String title, String type, String status,
                 Date releaseDate, String summary, Integer duration, String imgUrl, String trailerUrl) {
        this.id = id;
        this.genreList = genreList;
        this.title = title;
        this.type = type;
        this.status = status;
        this.releaseDate = releaseDate;
        this.summary = summary;
        this.duration = duration;
        this.imgUrl = imgUrl;
        this.trailerUrl = trailerUrl;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", genreList=" + getGenreString() +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", releaseDate=" + releaseDate +
                ", summary='" + summary + '\'' +
                ", duration=" + duration +
                ", imgUrl='" + imgUrl + '\'' +
                ", trailerUrl='" + trailerUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    public String getGenreString() {
        return String.join(", ", genreList);
    }

    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }

    public void addGenre(String genre) {
        this.genreList.add(genre);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDurationString() {
        return (int)(duration/60) + "m" + duration%60;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}

