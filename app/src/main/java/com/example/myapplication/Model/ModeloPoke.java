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

    public ModeloPoke() {
    }

    public ModeloPoke(String name, String url, Integer id) {
        this.name = name;
        this.url = url;
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPokeHeight(String pokeHeight) {
        this.pokeHeight = pokeHeight;
    }

    public void setPokeWeight(String pokeWeight) {
        this.pokeWeight = pokeWeight;
    }

    public void setPokeStats(List<PokeStats> pokeStats) {
        this.pokeStats = pokeStats;
    }

    public void setPokeTypes(List<PokeTypes> pokeTypes) {
        this.pokeTypes = pokeTypes;
    }

    public List<PokeStats> getPokeStats() {
        return pokeStats;
    }

    public List<PokeTypes> getPokeTypes() {
        return pokeTypes;
    }
}
