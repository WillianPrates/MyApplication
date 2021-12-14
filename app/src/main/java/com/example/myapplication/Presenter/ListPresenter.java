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

public class ListPresenter {

    private static final String TAG = "MainResponse";
    private List<ModeloPoke> pokemonListData = new ArrayList<>();


    public List<ModeloPoke> getPokemon(Context context){

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://pokeapi.co/api/v2/pokemon?limit=1050",
                this::onResponse,
                error -> Log.d(TAG, "onResponse: "+error)
        );

        Volley.newRequestQueue(context).add(request);

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
                                i +1
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
