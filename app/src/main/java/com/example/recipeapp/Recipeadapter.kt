package com.example.recipeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecipeAdapter(private var recipes: List<Meal>,private var ViewType: Int) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.recipe_image)
        val nameView: TextView = itemView.findViewById(R.id.recipe_name)
//        val animation: LottieAnimationView = itemView.findViewById(R.id.animation_view) // Add animation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        if(ViewType==1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
            return RecipeViewHolder(view)
        }else
        {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_recommended, parent, false)
            return RecipeViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.nameView.text = recipe.strMeal
        Glide.with(holder.itemView.context).load(recipe.strImageSource).into(holder.imageView)
//        holder.animation.setAnimation(R.raw.recipe_animation) // Example animation
//        holder.animation.playAnimation()
    }

    override fun getItemCount() = recipes.size

    fun updateRecipes(newRecipes: List<Meal>,viewType:Int) {
        recipes = newRecipes
        ViewType=viewType
        notifyDataSetChanged()
    }
}
