package com.example.dinu.catzz;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("/facts/random")
    Call<CatFacts> getFacts();
}
