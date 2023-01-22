package com.example.foody.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foody.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    lateinit var binding: FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(layoutInflater, container, false)

        showShimmer()

        return binding.root
    }

    fun hideSimmer(){
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.recipesRecycler.visibility = View.VISIBLE
    }

    fun showShimmer(){
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.recipesRecycler.visibility = View.GONE
    }
}