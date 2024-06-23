package com.example.recipeapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.Model.Meal
import com.example.recipeapp.R

class ChildAdapter(private var recipes: List<Meal>) : RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.recipe_image)
        val nameView: TextView = itemView.findViewById(R.id.recipe_name)
//        val animation: LottieAnimationView = itemView.findViewById(R.id.animation_view) // Add animation
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_recommended, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.nameView.text = recipe.strMeal
        Glide.with(holder.itemView.context).load(recipe.strMealThumb).into(holder.imageView)
//        holder.animation.setAnimation(R.raw.recipe_animation) // Example animation
//        holder.animation.playAnimation()
    }


    override fun getItemCount() = recipes.size

    fun updateRecipes(newRecipes: List<Meal>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
