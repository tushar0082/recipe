package com.example.recipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {
    private val repository = RecipeRepository()

    val recipes: LiveData<List<Meal>> get() = repository.recipes

    fun getRecipes() {
        repository.fetchRecipes()
    }
}
