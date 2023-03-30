package com.gsoft.yapechallenge.data.datasource

import com.gsoft.yapechallenge.data.model.Recipe
import retrofit2.http.GET

interface RecipeApi {
    @GET("getRecipes")
    suspend fun getRecipes() : List<Recipe>
}