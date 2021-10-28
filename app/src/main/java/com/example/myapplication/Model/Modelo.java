package com.example.myapplication.Model;

import java.io.Serializable;

public class Modelo implements Serializable {
    String name;
    String url;
    String pokeHeight;
    String pokeWeight;


    public Modelo(String name, String url) {
        this.name = name;
        this.url = url;
        this.pokeHeight = pokeHeight;
        this.pokeWeight = pokeWeight;
    }

    public String getPokeHeight() { return pokeHeight; }

    public String getPokeWeight() {return pokeWeight; }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
