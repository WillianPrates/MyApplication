package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Slide;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.myapplication.Model.ModeloPoke;
import com.example.myapplication.Model.PokeStats;
import com.example.myapplication.Model.PokeTypes;
import com.example.myapplication.Presenter.PokeList;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.json.JSONException;

/*



*/
public class PokeDetail extends AppCompatActivity {
    private Button bt_voltar;

    private Context context;

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
            ObjectMapper mapper = new ObjectMapper();

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

             /* Layout que irá receber os TextView
             */
            LinearLayout mContent = LinearLayout.class.cast(findViewById(R.id.mContent));
            LinearLayout mContent2 = LinearLayout.class.cast(findViewById(R.id.mContent2));
            /**
             * Lista de TextView que serão criados dinamicamente
             */
            List<TextView> textos = new ArrayList<>();
            List<TextView> textos2 = new ArrayList<>();

            // Ponteiroi
            int idPt = 1;
            int idPt2 = 1;

            for (PokeTypes listaDosTypos : listaTypes){
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

            for (PokeStats listaDosStatus : listaStatus){
                // Vamos criar a a instancia do TExtView
                final TextView txtItem2 = new TextView(this);
                // Informamos um id
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




