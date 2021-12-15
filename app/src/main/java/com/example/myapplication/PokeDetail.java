package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Model.ModeloPoke;
import com.example.myapplication.Model.PokeStats;
import com.example.myapplication.Model.PokeTypes;
import com.example.myapplication.Presenter.PokeInfos;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class PokeDetail extends AppCompatActivity {
    private Button bt_voltar;

    private TextView name;
    private TextView height;
    private TextView weight;
    private ImageView pokeImage;
    LinearLayout mContent;
    LinearLayout mContent2;

    private PokeInfos pokeInfos = new PokeInfos();
    private int position;

    private ModeloPoke pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_detail);

        Objects.requireNonNull(getSupportActionBar()).hide();
        IniciarComponentes();

        this.position = pokeInfos.getExtraPosition(savedInstanceState, getIntent());

        pokemon = pokeInfos.getPokemon(savedInstanceState, position, this, getIntent());

        buildPokemon(pokemon);

        Glide.with(PokeDetail.this)
                .load("https://cdn.traction.one/pokedex/pokemon/" + pokemon.getId() + ".png")
                .into(pokeImage);

        bt_voltar.setOnClickListener(view -> {

            Intent intent = new Intent(PokeDetail.this, PokeList.class);
            startActivity(intent);
            finish();
        });

    }

    public void IniciarComponentes() {
        bt_voltar = findViewById(R.id.bt_voltar);
        weight = findViewById(R.id.pokeWeight);
        name = findViewById(R.id.pokeName);
        height = findViewById(R.id.pokeHeight);
        pokeImage = findViewById(R.id.pokeImage);
        mContent = LinearLayout.class.cast(findViewById(R.id.mContent));
        mContent2 = LinearLayout.class.cast(findViewById(R.id.mContent2));
    }

    private static final String TAG = "MainResponse";

    private void buildPokemon(ModeloPoke poke){

        this.name.setText(poke.getName());

        StringRequest request = new StringRequest(
                Request.Method.GET,
                poke.getUrl(),
                this::onResponse,
                error -> Log.d(TAG, "onResponse: "+error)
        );

        Volley.newRequestQueue(this).add(request);

    }

    private void onResponse(String response) {
        try {

            JSONObject object = new JSONObject(response);

            Gson gson = new Gson();

            Type typeStats = new TypeToken<List<PokeStats>>() {}.getType();
            Type typeType = new TypeToken<List<PokeTypes>>() {}.getType();
            List<PokeStats> listaStatus = gson.fromJson(object.getString("stats"), typeStats);
            List<PokeTypes> listaTypes = gson.fromJson(object.getString("types"), typeType);
            String height = object.getString("height");
            String weight = object.getString("weight");

            this.height.setText(height);
            this.weight.setText(weight);

            int idPt = 1;
            int idPt2 = 1;

            for (PokeTypes listaDosTypos : listaTypes){
                final TextView txtItem = new TextView(this);
                txtItem.setId( idPt );
                idPt++;
                txtItem.setText(listaDosTypos.getType().getName());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.weight = 1.0f;
                txtItem.setGravity(Gravity.CENTER);
                txtItem.setLayoutParams(params);
                mContent.addView(txtItem);
            }

            for (PokeStats listaDosStatus : listaStatus){
                final TextView txtItem2 = new TextView(this);
                txtItem2.setId( idPt2 );
                idPt2++;
                txtItem2.setText(listaDosStatus.getStat().getName());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                params.weight = 1.0f;
                txtItem2.setGravity(Gravity.CENTER);
                txtItem2.setLayoutParams(params);
                mContent.addView(txtItem2);
            }

        }catch(JSONException e) {
            e.printStackTrace();

        }
    }
}




