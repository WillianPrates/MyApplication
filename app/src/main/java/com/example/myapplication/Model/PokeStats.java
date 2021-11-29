package com.example.myapplication.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokeStats {

    public Integer base_stat;
    @JsonProperty()
    public Integer effort;
    @JsonProperty()
    public Stat stat;

    public PokeStats(Integer baseStat, Integer effort, Stat stat) {
        this.base_stat = baseStat;
        this.effort = effort;
        this.stat = stat;
    }


    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
