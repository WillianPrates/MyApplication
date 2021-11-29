package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Model.ModeloPoke;
import com.example.myapplication.Presenter.PokeList;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONException;

/*
PokeStats pokeStats = mapper.readValue(object.getString("stats"),PokeStats.class);
ObjectMapper mapper = new ObjectMapper();


*/
public class PokeDetail extends AppCompatActivity {
    private Button bt_voltar;

    private TextView name;
    private TextView height;
    private TextView weight;
    private ImageView pokeImage;

    List<ModeloPoke> data = new ArrayList<>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_detail);
        Objects.requireNonNull(getSupportActionBar()).hide();
        IniciarComponentes();
        this.position = getExtraPosition(savedInstanceState);
        this.data = getExtraListPokemon(savedInstanceState);

        GetPokemon();
        popularDadoPokemon();

        Glide.with(PokeDetail.this)
                .load("https://cdn.traction.one/pokedex/pokemon/" + (data.get(position).getId()) + ".png")
                .into(pokeImage);

        bt_voltar.setOnClickListener(view -> {

            Intent intent = new Intent(PokeDetail.this, PokeList.class);
            startActivity(intent);
            finish();
        });
    }

    private void popularDadoPokemon() {
        ModeloPoke pokemon = data.get(position);

        weight.setText(pokemon.getPokeWeight());
        name.setText(pokemon.getName());
        height.setText(pokemon.getPokeHeight());
    }

    private ArrayList<ModeloPoke> getExtraListPokemon(Bundle savedInstanceState) {

        ArrayList<ModeloPoke> data = new ArrayList<>();

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                return data;
            } else data = (ArrayList<ModeloPoke>) extra.getSerializable("ListPokemon");
        }
        return data;
    }

    private int getExtraPosition(Bundle savedInstanceState) {
        int position = -1;
        String position2;

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                position = -1;
            } else {
                position2 = extra.getString("position");
                position = Integer.parseInt(position2);
            }
        }
        return position;
    }

    public void IniciarComponentes() {
        bt_voltar = findViewById(R.id.bt_voltar);
        weight = findViewById(R.id.pokeWeight);
        name = findViewById(R.id.pokeName);
        height = findViewById(R.id.pokeHeight);
        pokeImage = findViewById(R.id.pokeImage);
    }

    private static final String TAG = "MainResponse";

    private void GetPokemon(){

        StringRequest request = new StringRequest(
                Request.Method.GET,
                data.get(position).getUrl(),
                this::onResponse,
                error -> Log.d(TAG, "onResponse: "+error)
        );

        Volley.newRequestQueue(this).add(request);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void onResponse(String response) {
        Log.d(TAG, "onResponsePokedetail: " + response);


        try {
            JSONObject object = new JSONObject(response);
            //JSONArray array = object.getJSONArray("object");

            String height = object.getString("height");
            String weight = object.getString("weight");

            this.height.setText(height);
            this.weight.setText(weight);

        }catch(JSONException e) {
            e.printStackTrace();

        }


    }

}




