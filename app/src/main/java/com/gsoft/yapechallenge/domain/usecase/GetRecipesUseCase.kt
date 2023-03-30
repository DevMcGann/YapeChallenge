package com.gsoft.yapechallenge.domain.usecase

import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.domain.repository.RecipeRepository
import com.gsoft.yapechallenge.util.Resource
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repo: RecipeRepository) {
    suspend operator fun invoke(): Resource<List<Recipe>> {
        return repo.getRecipes()
    }
}