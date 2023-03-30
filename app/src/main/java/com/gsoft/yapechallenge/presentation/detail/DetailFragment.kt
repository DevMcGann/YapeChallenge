package com.gsoft.yapechallenge.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.gsoft.yapechallenge.R
import com.gsoft.yapechallenge.data.model.LocationData
import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.databinding.FragmentDetailBinding


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    lateinit var  recipe : Recipe

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        recipe = arguments?.get("recipe") as Recipe
        setView()
    }

    private fun setView() {
        context?.let {
            Glide.with(it).load(recipe?.image)
                .fitCenter().into(binding.recipeImg)
        }
        binding.recipeDesc.text = recipe?.description

        binding.btnMap.setOnClickListener {
            val locationData = LocationData(lat = recipe.lat, long = recipe.long)
            val bundle = bundleOf("location" to locationData)
            findNavController().navigate(R.id.action_detailFragment_to_mapFragment, bundle)
        }
    }
}