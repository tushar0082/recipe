package com.example.recipeapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipeapp.Model.Ingredient
import com.example.recipeapp.R

class IngredientAdapter(private val materialList:List<Ingredient>): RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {


    inner class IngredientViewHolder(itemView: View) :ViewHolder(itemView){
        val ingredientText=itemView.findViewById<TextView>(R.id.ingredient_txt)
        val ingredientQty=itemView.findViewById<TextView>(R.id.ingredient_qty_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_materials, parent, false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int {
       return materialList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
       holder.ingredientQty.text=materialList[position].quantity
       holder.ingredientText.text=materialList[position].itemName
    }
}