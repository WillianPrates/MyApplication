package com.example.myapplication.Presenter;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Modelo;

import java.util.ArrayList;

public class PokeInfos {

    private Button bt_deslogar2;

    Context context;
    ArrayList<Modelo> data;

    ImageView pokeImage;
    TextView pokeName, pokeHeight, pokeWeight;
    RecyclerView recyclerType, recyclerWeakness, recyclerEvolutionPrev, recyclerEvolutionNext;

    public PokeInfos(Context context, ArrayList<Modelo> data) {
        this.context = context;
        this.data = data;
    }


}
