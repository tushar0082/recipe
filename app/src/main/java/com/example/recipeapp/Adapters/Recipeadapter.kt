package com.example.recipeapp.Adapters

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.Model.Meal
import com.example.recipeapp.R
import com.example.recipeapp.UI.HomeFragment
import com.example.recipeapp.UI.RecipeExpandedFragment
import com.example.recipeapp.Utils.Utility

class RecipeAdapter(private var recipes: List<Meal>, private var ViewType: Int,private var listener:homeInterface) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    // View type constants
    private val VIEW_TYPE_VERTICAL = 1
    private val VIEW_TYPE_HORIZONTAL = 2
    var sizeTimer: CountDownTimer? =null


    private var isImageExpanded = false


    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView? = itemView.findViewById(R.id.recipe_image)
        val nameView: TextView? = itemView.findViewById(R.id.recipe_name)
        val recyclerView: RecyclerView? = itemView.findViewById(R.id.horizontal_recycler_view)
        val layoutCard: CardView? = itemView.findViewById(R.id.layout_reccyler)
//        val animation: LottieAnimationView = itemView.findViewById(R.id.animation_view) // Add animation
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {

        return when (viewType) {
            VIEW_TYPE_VERTICAL -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
                return RecipeViewHolder(view)
            }

            VIEW_TYPE_HORIZONTAL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.nested_recyler_item, parent, false)
                return RecipeViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }

    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        when (holder.itemViewType) {
            VIEW_TYPE_VERTICAL -> {
                val recipe = recipes[position]
                holder.nameView?.text = recipe.strMeal
                holder.imageView?.let {
                    Glide.with(holder.itemView.context).load(recipe.strMealThumb).into(
                        it
                    )
                }


                holder.imageView?.setOnClickListener {
                    listener.writeToViewModel(position)

                    val fragment = RecipeExpandedFragment()
                    val bundle = Bundle()
                    bundle.putInt("position", position)
                    fragment.arguments = bundle

                    val transaction = (holder.itemView.context as FragmentActivity).supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, fragment)
                    transaction.addToBackStack(null)  // Optional: Add fragment to back stack
                    transaction.commit()
                }
//                holder.recyclerMaterial?.layoutManager=LinearLayoutManager(   holder.itemView.context,
//                    LinearLayoutManager.HORIZONTAL,
//                    false)
//                holder.recyclerMaterial?.adapter=IngredientAdapter(Utility.getIngredientMeasurePairs(recipes[position]))
//                var isExpanded = false

//                holder.layoutCard?.setOnClickListener {
//                    if (!isExpanded) {
//
//                        it.setOnClickListener {
//                            val layoutParams = it.layoutParams as ViewGroup.MarginLayoutParams
//
//                            if (isExpanded) {
//                                // Shrink the item
//                                it.animate().setDuration(300).translationY(1f).withEndAction {
//                                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
//                                    layoutParams.setMargins(
//                                        holder.itemView.context.resources.getDimension(R.dimen.default_margin).toInt(),
//                                         holder.itemView.context.resources.getDimension(R.dimen.default_margin).toInt(),
//                                         holder.itemView.context.resources.getDimension(R.dimen.default_margin).toInt(),
//                                         holder.itemView.context.resources.getDimension(R.dimen.default_margin).toInt()
//                                    )
//                                    it.setPadding(
//                                         holder.itemView.context.resources.getDimension(R.dimen.default_padding).toInt(),
//                                         holder.itemView.context.resources.getDimension(R.dimen.default_padding).toInt(),
//                                         holder.itemView.context.resources.getDimension(R.dimen.default_padding).toInt(),
//                                         holder.itemView.context.resources.getDimension(R.dimen.default_padding).toInt()
//                                    )
//                                    it.layoutParams = layoutParams
//                                    isExpanded = false
//                                }
//                            } else {
//                                // Expand the item
//                                it.animate().setDuration(3000).translationY(10f).translationX(9f).withStartAction {
//                                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//                                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//                                    layoutParams.setMargins(0, 0, 0, 0)
//                                    it.setPadding(0, 0, 0, 0)
//                                    it.layoutParams = layoutParams
//                                    isExpanded = true
//                                    holder.layoutCard2?.visibility = View.VISIBLE
//                                }
//                            }
//                            it.requestLayout()
//                        }
//                    }
//                }





            }

            VIEW_TYPE_HORIZONTAL -> {

                holder.recyclerView?.layoutManager = LinearLayoutManager(
                    holder.itemView.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                val ChildAdapter = ChildAdapter(recipes)
                holder.recyclerView?.adapter = ChildAdapter
            }
        }

//        holder.animation.setAnimation(R.raw.recipe_animation) // Example animation
//        holder.animation.playAnimation()
    }

    override fun getItemCount() = recipes.size

    fun updateRecipes(newRecipes: List<Meal>, viewType: Int) {
        recipes = newRecipes
        ViewType = viewType
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        // Determine view type based on position
        return when {
            position < recipes.size - 1 -> VIEW_TYPE_VERTICAL
            position == recipes.size - 1 -> VIEW_TYPE_HORIZONTAL
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
fun expandView(view: View){

    val sizeListener =
        View.OnClickListener { view ->
            if (sizeTimer != null) sizeTimer!!.cancel()
            sizeTimer = object : CountDownTimer(1800, 100) {
                override fun onTick(l: Long) {
                    val params = view.getLayoutParams()
                    if (params.width <= 0) {
                        params.width = 400
                        params.height = 200
                    } else {
                        if (view.id == R.id.layout_reccyler) {
                            params.width = (params.width * 1.1).toInt()
                            params.height = (params.height * 1.1).toInt()
                        } else {
                            params.width = (params.width / 1.1).toInt()
                            params.height = (params.height / 1.1).toInt()
                        }
                    }
                    view.setLayoutParams(params)
                }

                override fun onFinish() {
                }
            }.start()
        }
    view.setOnClickListener(sizeListener)
}

}

interface homeInterface{

    fun writeToViewModel(meal:Int)
}

