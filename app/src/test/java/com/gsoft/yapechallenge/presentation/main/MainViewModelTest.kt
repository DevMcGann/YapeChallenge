package com.gsoft.yapechallenge.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gsoft.yapechallenge.MainCoroutineRule
import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.domain.usecase.GetRecipesUseCase
import com.gsoft.yapechallenge.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest{
    @RelaxedMockK
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @RelaxedMockK
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(getRecipesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when useCase return a list of products set on the livedata`() = runTest {
        //Given
        val recipe = Recipe(id = "asd", title = "asd", image = "asdasd", description = "asd", lat = "312", long = "423")
        val recipeList : List<Recipe> = listOf(recipe,recipe)
        val myList = Resource.Success(recipeList)

        coEvery { getRecipesUseCase() } returns myList

        //When
        mainViewModel.fetchRecipes()

        //Then
        advanceUntilIdle()
        assert(mainViewModel.recipes.value == myList)
    }
}