package com.example.myapplication.Presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Adapter;
import com.example.myapplication.FormLogin;
import com.example.myapplication.Model.ModeloPoke;
import com.example.myapplication.Model.PokeStats;
import com.example.myapplication.R;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PokeList extends AppCompatActivity {

    private static final String TAG = "MainResponse";
    List<ModeloPoke> data = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;
    private Button bt_deslogar;
    private EditText textPesquisaPokemon;
    private Button bt_pesquisarPokemon;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_list);
        getSupportActionBar().hide();

        IniciarComponentes();

        bt_deslogar.setOnClickListener(view -> {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(PokeList.this, FormLogin.class);
            startActivity(intent);
            finish();
        });

        bt_pesquisarPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPesquisaPokemon = findViewById(R.id.textPesquisaPokemon);
                List<ModeloPoke> resultListPokemon = data.stream().filter(pokemon -> pokemon.getName().contains(textPesquisaPokemon.getText())).collect(Collectors.toList());
                if(resultListPokemon.isEmpty()){
                    abrirToast();
                } else {
                    MontaLayout(resultListPokemon);
                }
            }
        });


        GetPokemon();

        MontaLayout(data);
       }

    private void abrirToast() {
        Toast.makeText(getApplicationContext(), "pokemon não encontrado", Toast.LENGTH_SHORT).show();
    }

    public void MontaLayout(List<ModeloPoke> resultListPokemon){
            recyclerView = findViewById(R.id.recyclerPoke);
            adapter = new Adapter(this, resultListPokemon);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(adapter);
       }

    public void IniciarComponentes(){
        bt_deslogar = findViewById(R.id.bt_deslogar);
        textPesquisaPokemon = findViewById(R.id.textPesquisaPokemon);
        bt_pesquisarPokemon = findViewById(R.id.bt_pesquisarPokemon);
    }

    private void GetPokemon(){

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://pokeapi.co/api/v2/pokemon?limit=1050",
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
                        new ModeloPoke(
                                object1.getString("name"),
                                object1.getString("url"),
                                i +1
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