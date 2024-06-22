package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php?f=a")
    fun getRecipes(): Call<Response>
}
