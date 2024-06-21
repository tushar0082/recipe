package com.example.recipeapp

data class Recipe(
    val id: String,
    val name: String,
    val imageUrl: String,
    // Add other fields as necessary
)

data class RecipeResponse(
    val recipes: List<Recipe>
)
