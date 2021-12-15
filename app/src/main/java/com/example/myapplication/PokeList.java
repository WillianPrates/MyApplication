package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.Adapter;
import com.example.myapplication.Model.ModeloPoke;
import com.example.myapplication.Presenter.ListPresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.stream.Collectors;

public class PokeList extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Button bt_deslogar;
    private EditText textPesquisaPokemon;
    private Button bt_pesquisarPokemon;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ListPresenter listPresenter = ListPresenter.getInstance();
        List<ModeloPoke>pokemonListData = listPresenter.getPokemon(this);
        adapter = new Adapter(this, pokemonListData);
        adapter.notifyDataSetChanged();


        setContentView(R.layout.activity_poke_list);
        getSupportActionBar().hide();

        IniciarComponentes();

        bt_deslogar.setOnClickListener(view -> {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(PokeList.this, FormLogin.class);
            startActivity(intent);
            finish();
        });

        bt_pesquisarPokemon.setOnClickListener(v -> {
            textPesquisaPokemon = findViewById(R.id.textPesquisaPokemon);
            List<ModeloPoke> resultListPokemon = pokemonListData.stream().filter(pokemon -> pokemon.getName().contains(textPesquisaPokemon.getText())).collect(Collectors.toList());
            if(resultListPokemon.isEmpty()){
                abrirToast();
            } else {
                MontaLayout(resultListPokemon);
            }
        });

        MontaLayout(pokemonListData);
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
}