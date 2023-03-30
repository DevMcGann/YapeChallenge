package com.gsoft.yapechallenge.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.domain.usecase.GetRecipesUseCase
import com.gsoft.yapechallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _recipes = MutableLiveData<Resource<List<Recipe>>>()
    val recipes: LiveData<Resource<List<Recipe>>> = _recipes


    init {
        fetchRecipes()
    }

    fun fetchRecipes(){
        viewModelScope.launch {
            _recipes.value = Resource.Loading
            try {
                _recipes.value = getRecipesUseCase.invoke()
            }catch (e:Exception){
                _recipes.value = Resource.Failure(e)
            }
        }
    }

}