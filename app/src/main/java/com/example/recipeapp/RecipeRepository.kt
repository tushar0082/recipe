package com.example.recipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> get() = _recipes

    fun fetchRecipes(ingredients: String) {
        RetrofitInstance.api.getRecipes(ingredients).enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(call: Call<RecipeResponse>, response: Response<RecipeResponse>) {
                if (response.isSuccessful) {
                    _recipes.postValue(response.body()?.recipes)
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                // Handle the error
            }
        })
    }
}
