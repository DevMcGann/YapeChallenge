package com.gsoft.yapechallenge.domain.repository

import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.util.Resource

interface RecipeRepository {
    suspend fun getRecipes(): Resource<List<Recipe>>
}