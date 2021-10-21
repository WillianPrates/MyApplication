package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Model.Modelo;

import java.util.ArrayList;

public class PokeDetail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_detail);
        getSupportActionBar().hide();
    }


    Context context;
    ArrayList<Modelo> data;

    ImageView pokeImage;
    TextView pokeName, pokeHeight, pokeWeight;
    RecyclerView recyclerType, recyclerWeakness, recyclerEvolutionPrev,recyclerEvolutionNext;

    /*public PokeDetail(Context context, ArrayList<Modelo> data) {
        this.context = context;
        this.data = data;
    }*/
}




