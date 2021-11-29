package com.example.myapplication.Model;

import java.io.Serializable;
import java.util.List;

public class ModeloPoke implements Serializable {
    public String name;
    public String url;
    public String pokeHeight;
    public String pokeWeight;
    public Integer id;
    public List<PokeStats> pokeStats;
    public List<PokeTypes> pokeTypes;


    public ModeloPoke(String name, String url, Integer id) {
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public ModeloPoke(String url, String pokeHeight, String pokeWeight) {
        this.url = url;
        this.pokeHeight = pokeHeight;
        this.pokeWeight = pokeWeight;
    }

    public ModeloPoke(String name, String url, String pokeHeight, String pokeWeight) {
        this.name = name;
        this.url = url;
        this.pokeHeight = pokeHeight;
        this.pokeWeight = pokeWeight;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {return id;}

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
