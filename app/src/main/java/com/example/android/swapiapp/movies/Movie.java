package com.example.android.swapiapp.movies;

import java.util.ArrayList;

public class Movie {
    private String title;
    private float episode_id;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;
    ArrayList< Object > characters = new ArrayList < Object > ();
    ArrayList < Object > planets = new ArrayList < Object > ();
    ArrayList < Object > starships = new ArrayList < Object > ();
    ArrayList < Object > vehicles = new ArrayList < Object > ();
    ArrayList < Object > species = new ArrayList < Object > ();
    private String created;
    private String edited;
    private String url;


    // Getter Methods
    public String getTitle() {
        return title;
    }

    public float getEpisode_id() {
        return episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public String getUrl() {
        return url;
    }

    // Setter Methods

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEpisode_id(float episode_id) {
        this.episode_id = episode_id;
    }

    public void setOpening_crawl(String opening_crawl) {
        this.opening_crawl = opening_crawl;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
