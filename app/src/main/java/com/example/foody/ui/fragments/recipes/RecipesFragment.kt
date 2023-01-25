package com.example.foody.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.viewmodels.MainViewModel
import com.example.foody.adapters.RecipesAdapter
import com.example.foody.databinding.FragmentRecipesBinding
import com.example.foody.util.Constants.Companion.API_KEY
import com.example.foody.util.NetworkResult
import com.example.foody.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {


    private val adapter by lazy { RecipesAdapter() }
    lateinit var binding: FragmentRecipesBinding


    private val recipesViewModel: RecipesViewModel by viewModels()
    private val  viewmodel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()

        getRecipesData()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recipesRecycler.adapter = adapter
        binding.recipesRecycler.layoutManager = LinearLayoutManager(requireContext())
        showShimmer()
    }

    private fun getRecipesData() {
        viewmodel.getRecipes(recipesViewModel.applyQueries())

        viewmodel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideSimmer()
                    response.data?.let { adapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    hideSimmer()
                    Toast.makeText(
                        requireContext(),
                        "Error occured in Recipes Fragment"+
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmer()
                }
            }
        }
    }


    private fun hideSimmer() {
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.recipesRecycler.visibility = View.VISIBLE
    }

    private fun showShimmer() {
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.recipesRecycler.visibility = View.GONE
    }
}