package com.example.myrickmorty;

import java.util.List;
import java.util.Vector;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/character")
    Call<JSONResponse> getCharacterFromPage(@Query("page") int page);
}
