package com.example.recipeapp.Api

import com.example.recipeapp.Model.Response
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("search.php?f=a")
    fun getRecipes(): Call<Response>
}
