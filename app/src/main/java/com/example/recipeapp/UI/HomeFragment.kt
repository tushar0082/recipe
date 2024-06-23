package com.example.recipeapp.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.Adapters.RecipeAdapter
import com.example.recipeapp.Adapters.homeInterface
import com.example.recipeapp.Model.Meal
import com.example.recipeapp.RecipeViewModel
import com.example.recipeapp.databinding.FragmentHomeBinding
import com.google.android.material.appbar.AppBarLayout

class HomeFragment : Fragment(),homeInterface{

    private lateinit var viewModel: RecipeViewModel
    private lateinit var verticalAdapter: RecipeAdapter
    private lateinit var appBarLayout: AppBarLayout
    private var isToolBarExpanded = true

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appBarLayout = binding.appBarLayout

        viewModel = ViewModelProvider(requireActivity())[RecipeViewModel::class.java]

        val verticalRecyclerView = binding.verticalRecyclerView
        verticalRecyclerView.layoutManager = LinearLayoutManager(context)
        verticalAdapter = RecipeAdapter(emptyList(), 1,this)
        verticalRecyclerView.adapter = verticalAdapter

        viewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            verticalAdapter.updateRecipes(recipes, 1)
        }

        // Add scroll listener to RecyclerView
        verticalRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if first item is fully visible
                val firstVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()

                if (firstVisibleItemPosition == 0 && !isToolBarExpanded) {
                    expandAppBar()
                } else if (firstVisibleItemPosition > 0 && isToolBarExpanded) {
                    collapseAppBar()
                }
            }
        })

            viewModel.getRecipes()


    }

    private fun expandAppBar() {
        if (!isToolBarExpanded) {
            appBarLayout.setExpanded(true, true)
            isToolBarExpanded = true
            binding.toolbarText.visibility=View.GONE
            binding.toolbarText2.visibility=View.VISIBLE
            binding.toolbarIcon.visibility=View.VISIBLE
        }
    }

    private fun collapseAppBar() {
        if (isToolBarExpanded) {
            appBarLayout.setExpanded(false, true)
            isToolBarExpanded = false
            binding.toolbarText.visibility=View.VISIBLE
            binding.toolbarText2.visibility=View.GONE
            binding.toolbarIcon.visibility=View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun writeToViewModel(position: Int) {

    }

}
