package com.example.recipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.recipeapp.Api.RecipeRepository
import com.example.recipeapp.Model.Meal

class RecipeViewModel : ViewModel() {
    private val repository = RecipeRepository()

    val recipes: LiveData<List<Meal>> get() = repository.recipes
    lateinit var meal: Meal

    fun getRecipes() {
        repository.fetchRecipes()
    }
}
