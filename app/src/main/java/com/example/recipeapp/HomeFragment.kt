package com.example.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var viewModel: RecipeViewModel
    private lateinit var verticalAdapter: RecipeAdapter
    private lateinit var horizontalAdapter: RecipeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.layoutManager = LinearLayoutManager(context)
        verticalAdapter = RecipeAdapter(emptyList())
        verticalRecyclerView.adapter = verticalAdapter

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        horizontalAdapter = RecipeAdapter(emptyList())
        horizontalRecyclerView.adapter = horizontalAdapter

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
//            verticalAdapter.updateRecipes(recipes)
//            horizontalAdapter.updateRecipes(recipes)
        }

        val button=view.findViewById<TextView>(R.id.button)

        button.setOnClickListener{
            viewModel.getRecipes("chocolate")
        }


    }


}