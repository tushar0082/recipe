package com.example.recipeapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipeapp.Adapters.IngredientAdapter
import com.example.recipeapp.Model.Meal
import com.example.recipeapp.RecipeViewModel
import com.example.recipeapp.Utils.Utility
import com.example.recipeapp.databinding.FragmentRecipeExpandedBinding


class RecipeExpandedFragment : Fragment() {

    private lateinit var binding: FragmentRecipeExpandedBinding
    private lateinit var meal: Meal
    private lateinit var viewModel: RecipeViewModel

    var position=-1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeExpandedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Access views using binding

        arguments?.let {
            position = it.getInt("position", -1)
        }
        viewModel = ViewModelProvider(requireActivity())[RecipeViewModel::class.java]

        meal = viewModel.recipes.value?.get(position)!!


        binding.recipeName.text = meal.strMeal
        binding.txtDescription.text = meal.strInstructions
        binding.recipeImage?.let {
            context?.let { it1 ->
                Glide.with(it1).load(meal.strMealThumb).into(
                    it
                )
            }
        }

        binding.recylerIngredients.layoutManager= LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recylerIngredients.adapter=IngredientAdapter(Utility.getIngredientMeasurePairs(meal))




        binding.back.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()

        }


    }
}