package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.Adapter;
import com.example.myapplication.Model.Modelo;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Produto extends AppCompatActivity {

    private static String TAG = "MainResponse";
    ArrayList<Modelo> data = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;
    ProgressDialog pd;
    private Button bt_deslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        getSupportActionBar().hide();

        IniciarComponentes();

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Produto.this,FormLogin.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerPoke);

        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Carregando... ");

        adapter = new Adapter(this,data);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

        GetPokemon("https://pokeapi.co/api/v2/pokemon?limit=1050");

        //TESTE CLICK #################


    }

    private void GetPokemon(String url){

        pd.show();

        StringRequest request = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Log.d(TAG, "onResponse: "+response);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("results");

                    for(int i = 0; i < array.length(); i ++){
                        JSONObject object1 = array.getJSONObject(i);

                        data.add(
                                new Modelo(
                                        object1.getString("name"),
                                        object1.getString("url")
                                )
                        );

                        Log.d(TAG, "onResponse: "+data.get(i).getName());


                    }

                    adapter.notifyDataSetChanged();

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.d(TAG, "onResponse: "+error);

            }
        }
        );

        Volley.newRequestQueue(this).add(request);
    }
    private void IniciarComponentes(){
        bt_deslogar = findViewById(R.id.bt_deslogar);
    }


}