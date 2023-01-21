package com.example.myrickmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Character> characterList= new ArrayList<>();
    public RecyclerView mRecyclerView;
    private Context instance = this;
    private Adapter myCharactersAdapter = new Adapter(characterList,mRecyclerView,instance);
    private final AtomicInteger requestCounter = new AtomicInteger(1);
    private final AtomicInteger responseCounter = new AtomicInteger(0);

    private void putDataInRecyclerView(List<Character> characters){
        Collections.sort(characterList, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Integer.compare(c1.getId(),c2.getId());
            }
        });
        myCharactersAdapter = new Adapter(characters,mRecyclerView,instance);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.mainRecyclerView);
        mRecyclerView.setAdapter(myCharactersAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService ourRetrofitClient = retrofit.create(ApiService.class);
        addtoList(ourRetrofitClient,42);

    }

    private void addtoList(ApiService ourRetrofitClient,int pageNumber){
        for (int j = 1; j <= pageNumber; j++) {
            Call<JSONResponse> call = ourRetrofitClient.getCharacterFromPage(j);
            call.enqueue(new Callback<JSONResponse>() {
                @Override
                public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                    if (response.isSuccessful()) {
                        JSONResponse jsonResponse = response.body();
                        responseCounter.incrementAndGet();
                        characterList.addAll(Arrays.asList(jsonResponse.getResults()));
                        if(responseCounter.get() == requestCounter.get()){
                            // All requests are done and responses are in correct
                            putDataInRecyclerView(characterList);
                        }

                    }
                }
                @Override
                public void onFailure(Call<JSONResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        requestCounter.set(pageNumber);
    }



}