package com.example.recipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class RecipeViewModel : ViewModel() {
    private val repository = RecipeRepository()

    val recipes: LiveData<List<Recipe>> get() = repository.recipes

    fun getRecipes(ingredients: String) {
        repository.fetchRecipes(ingredients)
    }
}
