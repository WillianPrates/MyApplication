package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Model.Modelo;
import com.example.myapplication.Presenter.Produto;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class PokeDetail extends AppCompatActivity {

    private Button bt_deslogar2;
    private Button bt_voltar;

    private TextView pokeName;
    private TextView pokeHeight;
    private TextView pokeWeight;

    ArrayList<Modelo> data = new ArrayList<>();
    ProgressDialog pd;
    private static final String TAG = "MainResponse";

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_detail);
        getSupportActionBar().hide();
        IniciarComponentes();
        this.position = getExtraPosition(savedInstanceState);
        this.data = getExtraListPokemon(savedInstanceState);
        popularDadoPokemon();


        bt_deslogar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(PokeDetail.this, FormLogin.class);
                startActivity(intent);
                finish();
            }
        });

        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PokeDetail.this, Produto.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void popularDadoPokemon() {
        Modelo pokemon = data.get(position);

        pokeWeight.setText(pokemon.getPokeWeight());
        pokeName.setText(pokemon.getName());
        pokeHeight.setText(pokemon.getPokeHeight());

    }

    private ArrayList<Modelo> getExtraListPokemon(Bundle savedInstanceState) {

        ArrayList<Modelo> data = new ArrayList<>();

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if(extra == null) {
                return data;
            } else {
                data = (ArrayList<Modelo>) extra.getSerializable("ListPokemon");
            }
        }
        return data;
    }

    private int getExtraPosition(Bundle savedInstanceState) {
        int position = -1;
        String position2;

        if (savedInstanceState == null) {
            Bundle extra = getIntent().getExtras();
            if(extra == null) {
                position = -1;
            } else {
                position2 = extra.getString("position");
                position= Integer.parseInt(position2);
            }
        }

        return position;
    }


    public void IniciarComponentes(){

        bt_deslogar2 = findViewById(R.id.bt_deslogar2);
        bt_voltar = findViewById(R.id.bt_voltar);
        pokeWeight = findViewById(R.id.pokeWeight);
        pokeName = findViewById(R.id.pokeName);
        pokeHeight = findViewById(R.id.pokeHeight);

    }
}




