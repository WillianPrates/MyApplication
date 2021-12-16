package com.example.myapplication.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.Model.ModeloPoke;

import java.util.List;

public class PokeInfos {

    public int getExtraPosition(Bundle savedInstanceState, Intent intent) {
        int position = -1;
        String position2;

        if (savedInstanceState == null) {
            Bundle extra = intent.getExtras();
            if (extra == null) {
                position = -1;
            } else {
                position2 = extra.getString("position");
                position = Integer.parseInt(position2);
            }
        }
        return position;
    }

    public ModeloPoke getPokemon(Bundle savedInstanceState, Integer position, Context context, Intent intent){

        ModeloPoke pokemonExtraListPokemon = getPokemonExtraListPokemon(savedInstanceState, intent, position);

        return pokemonExtraListPokemon;
    }

    private ModeloPoke getPokemonExtraListPokemon(Bundle savedInstanceState, Intent intent, Integer position) {

        if (savedInstanceState == null) {
            Bundle extra = intent.getExtras();
            if (extra == null) {
                return null;
            } else {
                List<ModeloPoke> listPokemon = (List<ModeloPoke>) extra.getSerializable("ListPokemon");

                ModeloPoke modeloPoke = listPokemon.get(position);

                return modeloPoke;
            }
        }
        return null;
    }

}
