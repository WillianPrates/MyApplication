package com.example.myapplication.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Interface.IItemClickListener;
import com.example.myapplication.Model.ModeloPoke;
import com.example.myapplication.PokeDetail;
import com.example.myapplication.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<ModeloPoke> data;


    public Adapter(Context context, List<ModeloPoke> data) {
        this.context = context;
        this.data = data;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .asBitmap()
                .load("https://cdn.traction.one/pokedex/pokemon/" + data.get(position).getId() + ".png").into(holder.image);

        holder.name.setText(data.get(position).getName());

        holder.setiItemClickListener((view, position1) -> {
            Toast.makeText(context, "Pokemon clicado: " + data.get(position1).getName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, PokeDetail.class);
            intent.putExtra("position", String.valueOf(position1));
            intent.putExtra("ListPokemon", (Serializable) data);
            view.getContext().startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView name;

        IItemClickListener iItemClickListener;

        public void setiItemClickListener(IItemClickListener iItemClickListener) {
            this.iItemClickListener = iItemClickListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.name = itemView.findViewById(R.id.name_pokemon);

            itemView.setOnClickListener(this);

        }

        public void onClick(View view) {
            iItemClickListener.onClick(view, getAdapterPosition());
        }
    }


}

