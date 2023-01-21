package com.example.myrickmorty;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<Character> charactersList = new ArrayList<>();
    private RecyclerView mRecyclerView;

    private Context ctx;



    Adapter(List<Character> c, RecyclerView mRecyclerView, Context ctx){
        this.charactersList = c;
        this.mRecyclerView = mRecyclerView;
        this.ctx = ctx;

    }



    public void setImage(String imageURL, ImageView imageView){
        Glide.with(ctx)
                .load(imageURL)
                .into(imageView);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.character_design, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final Character character = charactersList.get(position);
        final int i = position;
        holder.display(character);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,CharacterInfoActivity.class);
                intent.putExtra("character",character);
                ctx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount(){
        return charactersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView itemName;
        private ImageView itemPicture;
        private TextView itemGender;
        private TextView itemStatus;
        private TextView itemId;



        ViewHolder(View itemView){
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            itemId = (TextView)itemView.findViewById(R.id.itemId);
            itemGender = (TextView) itemView.findViewById(R.id.itemGender);
            itemPicture = (ImageView) itemView.findViewById(R.id.itemImage);
            itemStatus = (TextView) itemView.findViewById(R.id.itemStatus);


        }

        void display(Character c){
            itemName.setText(c.getName());
            itemGender.setText(c.getGender());
            itemId.setText(Integer.toString(c.getId()));
            itemStatus.setText("("+c.getStatus()+")");

            setImage(c.getImg(),itemPicture);

        }
    }
}
