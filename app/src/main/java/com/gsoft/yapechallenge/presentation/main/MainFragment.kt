package com.gsoft.yapechallenge.presentation.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.gsoft.yapechallenge.R
import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.databinding.FragmentMainBinding
import com.gsoft.yapechallenge.presentation.main.adapter.RecipeAdapter
import com.gsoft.yapechallenge.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var recipeAdapter: RecipeAdapter

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding

    var recipeList : MutableList<Recipe> = mutableListOf()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        observe()
        setView()
        fetchRecipes()
        listenSearch()

    }

    private fun listenSearch() {
        binding.eQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
               if (s.length > 3){
                   val filtered = recipeList.filter { it.title.lowercase(Locale.ROOT).contains(s) }
                   recipeAdapter.submitList(filtered)
                   recipeAdapter.notifyDataSetChanged()
               }else{
                   recipeAdapter.submitList(recipeList)
                   recipeAdapter.notifyDataSetChanged()
               }
            }
        })
    }

    private fun setView() {
        recipeAdapter.setOnItemClickListener {
            val bundle = bundleOf("recipe" to it)
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
        binding.rvRecipes.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }
    }


    private fun fetchRecipes() {
        viewModel.fetchRecipes()
    }


    private fun observe() {
        viewModel.recipes.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    handleLoading(true)
                }
                is Resource.Success -> {
                    handleLoading(false)
                    recipeList = resource.data.toMutableList()
                   recipeAdapter.submitList(recipeList)
                }
                is Resource.Failure -> {
                    handleLoading(false)
                    val exception = resource.exception
                    view?.let {
                        Snackbar.make(
                            it,
                            exception.message.toString(),
                            Snackbar.LENGTH_LONG
                        )
                    }?.show()
                    Log.d("ERROR", exception.message.toString())
                }
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.isVisible = true
            binding.progressBar.isGone = false
        } else {
            binding.progressBar.isGone = true
            binding.progressBar.isVisible = false
        }
    }


}