package com.gsoft.yapechallenge.domain.usecase



import com.gsoft.yapechallenge.data.model.Recipe
import com.gsoft.yapechallenge.domain.repository.RecipeRepository
import com.gsoft.yapechallenge.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetRecipesUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: RecipeRepository

    lateinit var useCase: GetRecipesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        useCase = GetRecipesUseCase(repository)
    }

    @Test
    fun `get recipes from API success`() = runBlocking {
        //Given
        val recipe = Recipe(id = "asd", title = "asd", image = "asdasd", description = "asd", lat = "312", long = "423")
        val recipeList : List<Recipe> = listOf(recipe,recipe)
        val myList = Resource.Success(recipeList)

        coEvery { repository.getRecipes() } returns myList

        //When
        val sut = useCase()

        //Then
        coVerify(exactly = 1) { repository.getRecipes() }
        assert(sut == myList)
    }

    @Test
    fun `get products fro API failure`() = runBlocking {
        //Given
        val exception = Resource.Failure(Exception())
        coEvery { repository.getRecipes() } returns exception

        //When
        val sut = useCase()

        //Then
        coVerify(exactly = 1) { repository.getRecipes() }
        assert(sut == exception)
    }


}