package com.example.myapplication.Model;

import java.io.Serializable;

public class Modelo implements Serializable {
    public String name;
    public String url;
    public String pokeHeight;
    public String pokeWeight;


    public Modelo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Modelo(String url, String pokeHeight, String pokeWeight) {
        this.url = url;
        this.pokeHeight = pokeHeight;
        this.pokeWeight = pokeWeight;
    }

    public Modelo(String name, String url, String pokeHeight, String pokeWeight) {
        this.name = name;
        this.url = url;
        this.pokeHeight = pokeHeight;
        this.pokeWeight = pokeWeight;
    }


    public String getPokeHeight() {
        return pokeHeight;
    }

    public String getPokeWeight() {
        return pokeWeight;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
