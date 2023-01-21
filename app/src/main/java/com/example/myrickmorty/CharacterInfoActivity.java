package com.example.myrickmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class CharacterInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_info_activity);
        Character c = getIntent().getParcelableExtra("character");
        ImageView itemImg = (ImageView) findViewById(R.id.itemInfoImg);
        TextView itemName = (TextView) findViewById(R.id.itemInfoName);
        TextView itemStatus = (TextView) findViewById(R.id.itemInfoStatus2);
        TextView itemGender = (TextView) findViewById(R.id.itemInfoGender);
        TextView itemEpisodes = (TextView)findViewById(R.id.itemEpisode);
        TextView itemLocation = (TextView)findViewById(R.id.itemInfoLocation);

        setImage(c.getImg(),itemImg);
        itemName.setText(c.getName());
        itemStatus.setText(c.getStatus());
        itemGender.setText(c.getGender());
        itemLocation.setText(c.getLocation().getName());

        String s = "";
        List<String> list = c.getEpisode();
        for (String i : list){
            s+= i + "\n";
        }
        itemEpisodes.setText(s);


    }

    public void setImage(String imageURL, ImageView imageView){
        Glide.with(this)
                .load(imageURL)
                .into(imageView);

    }
}