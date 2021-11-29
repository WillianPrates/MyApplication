package com.example.myapplication.Presenter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Adapter;
import com.example.myapplication.FormLogin;
import com.example.myapplication.Model.Modelo;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class Produto extends AppCompatActivity {

    private static final String TAG = "MainResponse";
    ArrayList<Modelo> data = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;
    private Button bt_deslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        Objects.requireNonNull(getSupportActionBar()).hide();

        IniciarComponentes();

        bt_deslogar.setOnClickListener(view -> {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(Produto.this, FormLogin.class);
            startActivity(intent);
            finish();
        });

        recyclerView = findViewById(R.id.recyclerPoke);

        adapter = new Adapter(this,data);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

       GetPokemon();

       }

    public void IniciarComponentes(){
        bt_deslogar = findViewById(R.id.bt_deslogar);
    }

    private void GetPokemon(){

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://pokeapi.co/api/v2/pokemon/",
                this::onResponse,
                error -> Log.d(TAG, "onResponse: "+error)
        );

        Volley.newRequestQueue(this).add(request);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void onResponse(String response) {
        Log.d(TAG, "onResponse: " + response);

        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("results");

            for (int i = 0; i < array.length(); i++) {

                JSONObject object1 = array.getJSONObject(i);

                data.add(
                        new Modelo(
                                object1.getString("name"),
                                object1.getString("url")
                        )
                );

                Log.d(TAG, "onResponse: " + data.get(i).getName());

            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}