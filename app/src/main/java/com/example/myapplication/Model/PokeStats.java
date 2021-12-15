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

    public Integer getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(Integer base_stat) {
        this.base_stat = base_stat;
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
