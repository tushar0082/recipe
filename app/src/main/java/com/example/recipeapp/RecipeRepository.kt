package com.example.recipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository {
    private val _recipes = MutableLiveData<List<Meal>>()
    val recipes: LiveData<List<Meal>> get() = _recipes

    fun fetchRecipes() {
        RetrofitInstance.api.getRecipes().enqueue(object : Callback<com.example.recipeapp.Response> {
            override fun onResponse(
                call: Call<com.example.recipeapp.Response>,
                response: Response<com.example.recipeapp.Response>
            ) {
                if (response.isSuccessful) {
                    _recipes.postValue(response.body()?.meals)
                }
            }

            override fun onFailure(call: Call<com.example.recipeapp.Response>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
