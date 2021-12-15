package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.ModeloPoke;
import com.example.myapplication.Model.PokeStats;
import com.example.myapplication.Model.PokeTypes;
import com.example.myapplication.Presenter.PokeInfos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PokeDetail extends AppCompatActivity {
    private Button bt_voltar;

    private TextView name;
    private TextView height;
    private TextView weight;
    private ImageView pokeImage;

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

        popularDadoPokemon(pokemon);

        Glide.with(PokeDetail.this)
                .load("https://cdn.traction.one/pokedex/pokemon/" + position + ".png")
                .into(pokeImage);

        bt_voltar.setOnClickListener(view -> {

            Intent intent = new Intent(PokeDetail.this, PokeList.class);
            startActivity(intent);
            finish();
        });

    }

    public void popularDadoPokemon(ModeloPoke pokemon) {

        weight.setText(pokemon.getPokeWeight());
        name.setText(pokemon.getName());
        height.setText(pokemon.getPokeHeight());

        montarAtributos(pokemon);
    }

    public void IniciarComponentes() {
        bt_voltar = findViewById(R.id.bt_voltar);
        weight = findViewById(R.id.pokeWeight);
        name = findViewById(R.id.pokeName);
        height = findViewById(R.id.pokeHeight);
        pokeImage = findViewById(R.id.pokeImage);

    }

    public void montarAtributos(ModeloPoke modeloPoke){

        LinearLayout mContent = LinearLayout.class.cast(findViewById(R.id.mContent));
        LinearLayout mContent2 = LinearLayout.class.cast(findViewById(R.id.mContent2));


        int idPt = 1;
        int idPt2 = 1;

        for (PokeTypes listaDosTypos : modeloPoke.getPokeTypes()){
            // Vamos criar a a instancia do TExtView
            final TextView txtItem = new TextView(this);
            // Informamos um id
            txtItem.setId( idPt );
            idPt++;
            txtItem.setText(listaDosTypos.getType().getName());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            txtItem.setGravity(Gravity.CENTER);
            txtItem.setLayoutParams(params);
            mContent.addView(txtItem);
        }

        for (PokeStats listaDosStatus : modeloPoke.getPokeStats()){
            // Vamos criar a a instancia do TExtView
            final TextView txtItem2 = new TextView(this);
            // Informamos um id
            txtItem2.setId( idPt2 );
            idPt2++;
            txtItem2.setText(listaDosStatus.getBase_stat());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1.0f;
            txtItem2.setGravity(Gravity.CENTER);
            txtItem2.setLayoutParams(params);
            mContent2.addView(txtItem2);
        }

    }
}




