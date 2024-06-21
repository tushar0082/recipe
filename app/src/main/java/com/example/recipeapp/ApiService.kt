package com.example.recipeapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes")
    fun getRecipes(@Query("ingrediets") ingredients: String): Call<RecipeResponse>
}
