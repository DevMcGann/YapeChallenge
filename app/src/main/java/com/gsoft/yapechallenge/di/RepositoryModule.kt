package com.gsoft.yapechallenge.di


import com.gsoft.yapechallenge.data.datasource.RecipeApi
import com.gsoft.yapechallenge.domain.repository.RecipeRepository
import com.gsoft.yapechallenge.domain.repository.RecipeRepositoryImpl
import com.gsoft.yapechallenge.domain.usecase.GetRecipesUseCase
import com.gsoft.yapechallenge.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRecipeRepository(
        apiService: RecipeApi,

        ): RecipeRepository {
        return RecipeRepositoryImpl(
            api = apiService
        )
    }

    @Provides
    fun provideUseCases(
        repo: RecipeRepository,
    ) = UseCases(
        recipesUseCase = GetRecipesUseCase(repo)
    )


}