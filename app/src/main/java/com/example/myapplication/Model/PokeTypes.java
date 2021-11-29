package com.example.myapplication.Model;

public class PokeTypes {
    public Integer slot;
    public Type type;

    public PokeTypes(Integer slot, Type type) {
        this.slot = slot;
        this.type = type;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
