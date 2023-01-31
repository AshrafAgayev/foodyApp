package com.example.foody.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.R
import com.example.foody.viewmodels.MainViewModel
import com.example.foody.adapters.RecipesAdapter
import com.example.foody.databinding.FragmentRecipesBinding
import com.example.foody.util.NetworkResult
import com.example.foody.util.observeOnce
import com.example.foody.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { RecipesAdapter() }

    private val recipesViewModel: RecipesViewModel by viewModels()
    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = viewmodel

        setupRecyclerView()
        readDatabase()

        binding.recipesFab.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recipesRecycler.adapter = adapter
        binding.recipesRecycler.layoutManager = LinearLayoutManager(requireContext())
        showShimmer()
    }


    private fun getRecipesData() {
        Log.d("Recipes Fragment", "Requested APi call")
        viewmodel.getRecipes(recipesViewModel.applyQueries())

        viewmodel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmer()
                    response.data?.let { adapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmer()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        "Error occured in Recipes Fragment" +
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

    fun loadDataFromCache() {
        lifecycleScope.launch() {
            viewmodel.readRecipes.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    adapter.setData(database[0].foodRecipe)
                }
            }
        }
    }

    fun readDatabase() {
        lifecycleScope.launch{
            viewmodel.readRecipes.observeOnce(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    Log.d("Recipes Fragment", "Read Database called")
                    adapter.setData(database[0].foodRecipe)
                    hideShimmer()
                } else {
                    getRecipesData()
                }
            }
        }
    }


    private fun hideShimmer() {
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.recipesRecycler.visibility = View.VISIBLE
    }

    private fun showShimmer() {
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.recipesRecycler.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}