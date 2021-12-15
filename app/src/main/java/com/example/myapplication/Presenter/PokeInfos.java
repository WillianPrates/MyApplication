package com.example.myapplication.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Model.ModeloPoke;
import com.example.myapplication.Model.PokeStats;
import com.example.myapplication.Model.PokeTypes;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PokeInfos {

    private ModeloPoke modeloPoke = new ModeloPoke();
    private ListPresenter listPresenter = ListPresenter.getInstance();


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

    private static final String TAG = "MainResponse";

    public ModeloPoke getPokemon(Bundle savedInstanceState, Integer position, Context context, Intent intent){

        getPokemonExtraListPokemon(savedInstanceState, intent, position);

        List<ModeloPoke> whatever = ListPresenter.getInstance().getPokemon(context);

        StringRequest request = new StringRequest(

                Request.Method.GET,
                whatever.get(position).getUrl(),
                this::onResponse,
                error -> Log.d(TAG, "onResponse: "+error)
        );

        Volley.newRequestQueue(context).add(request);

        return modeloPoke;
    }

    private ModeloPoke getPokemonExtraListPokemon(Bundle savedInstanceState, Intent intent, Integer position) {

        if (savedInstanceState == null) {
            Bundle extra = intent.getExtras();
            if (extra == null) {
                return null;
            } else {
                List<ModeloPoke> listPokemon = (List<ModeloPoke>) extra.getSerializable("ListPokemon");

                return listPokemon
                        .stream()
                        .filter(e -> e.getId() == position)
                        .findAny().get();
            }
        }
        return null;
    }


    private ModeloPoke onResponse(String response) {

        Log.d(TAG, "onResponsePokedetail: " + response);


        try {
            JSONObject object = new JSONObject(response);

            Gson gson = new Gson();

            Type typeStats = new TypeToken<List<PokeStats>>() {}.getType();
            Type typeType = new TypeToken<List<PokeTypes>>() {}.getType();

            List<PokeStats> listaStatus = gson.fromJson(object.getString("stats"), typeStats);
            List<PokeTypes> listaTypes = gson.fromJson(object.getString("types"), typeType);

            String height = object.getString("height");
            String weight = object.getString("weight");

            modeloPoke.setPokeHeight(height);
            modeloPoke.setPokeWeight(weight);
            modeloPoke.setPokeTypes(listaTypes);
            modeloPoke.setPokeStats(listaStatus);

        }catch(JSONException e) {
            e.printStackTrace();

        }

        return modeloPoke;
    }
}
