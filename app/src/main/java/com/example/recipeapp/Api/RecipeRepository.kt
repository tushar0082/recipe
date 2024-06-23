package com.example.recipeapp.Api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recipeapp.Model.Meal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository {
    private val _recipes = MutableLiveData<List<Meal>>()
    val recipes: LiveData<List<Meal>> get() = _recipes

    fun fetchRecipes() {
        RetrofitInstance.api.getRecipes().enqueue(object : Callback<com.example.recipeapp.Model.Response> {
            override fun onResponse(
                call: Call<com.example.recipeapp.Model.Response>,
                response: Response<com.example.recipeapp.Model.Response>
            ) {
                if (response.isSuccessful) {
                    _recipes.postValue(response.body()?.meals)
                }
            }

            override fun onFailure(call: Call<com.example.recipeapp.Model.Response>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
