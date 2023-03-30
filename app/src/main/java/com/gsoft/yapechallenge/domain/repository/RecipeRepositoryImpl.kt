package com.gsoft.yapechallenge.domain.repository

import android.util.Log
import com.gsoft.yapechallenge.data.datasource.RecipeApi
import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.util.Resource
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api : RecipeApi
) : RecipeRepository {
    override suspend fun getRecipes(): Resource<List<Recipe>>{
        return try {
            val response = api.getRecipes()
            Log.d("DATA", response.toString())
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}