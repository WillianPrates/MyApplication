package com.example.myapplication.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Model.ModeloPoke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class ListPresenter {

    private static final String TAG = "MainResponse";
    private static final List<ModeloPoke> pokemonListData = new ArrayList<>();

    private ListPresenter(){

    }

    public static List<ModeloPoke> getInstance(Context context){
        return new ListPresenter().getPokemon(context);
    }

    public List<ModeloPoke> getPokemon(Context context){

        if (pokemonListData.isEmpty()){
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    "https://pokeapi.co/api/v2/pokemon?limit=1050",
                    this::onResponse,
                    error -> Log.d(TAG, "onResponse: "+error)
            );

            Volley.newRequestQueue(context).add(request);
        }

        return pokemonListData;
    }

    private List<ModeloPoke> onResponse(String response) {
        Log.d(TAG, "onResponse: " + response);

        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("results");

            for (int i = 0; i < array.length(); i++) {

                JSONObject object1 = array.getJSONObject(i);

                pokemonListData.add(
                        new ModeloPoke(
                                object1.getString("name"),
                                object1.getString("url"),
                                i
                        )
                );

                Log.d(TAG, "onResponse: " + pokemonListData.get(i).getName());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pokemonListData;
    }
}
