package com.engie.eea_tech_interview

import com.engie.eea_tech_interview.model.Movie
import com.engie.eea_tech_interview.model.MoviesList
import com.engie.eea_tech_interview.model.SearchResult
import com.engie.eea_tech_interview.network.api.MovieApiService
import com.engie.eea_tech_interview.network.repositories.MoviesRepository
import com.engie.eea_tech_interview.viewModel.LoadingState
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MoviesRepositoryTest {
    @Mock
    private lateinit var apiService: MovieApiService

    private lateinit var repository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = MoviesRepository(apiService)
    }

    @Test
    fun testSearchMovies() = runBlocking {
        val query = "test"
        val mockResponse = Response.success(SearchResult(results = listOf(
            Movie(1,true,"","","","","Test Movie 1", emptyList(),"","","",0.0,0,false),
            Movie(1,true,"","","","","Test Movie 2", emptyList(),"","","",0.0,0,false)
        )))

        Mockito.`when`(apiService.getMovies(query = query)).thenReturn(mockResponse)

        val response = repository.searchMovies(query)
        Assert.assertEquals(mockResponse, response)
    }

    @Test
    fun testGetMoviesList() = runBlocking {
        val page = 1
        val mockMovies = listOf(
            Movie(1,true,"","","","","Test Movie 1", emptyList(),"","","",0.0,0,false),
            Movie(1,true,"","","","","Test Movie 2", emptyList(),"","","",0.0,0,false)
        )
        val mockResponse = MoviesList(1,mockMovies,1,1)

        Mockito.`when`(apiService.getMoviesList(page = page)).thenReturn(mockResponse)

        repository.getMoviesList(page).collect { loadingState ->
            when (loadingState) {
                is LoadingState.Success -> {
                    Assert.assertEquals(mockMovies, loadingState.data)
                }
                is LoadingState.Error -> {
                    Assert.fail("Expected Success but got Error")
                }
                is LoadingState.ShowLoading -> {
                    // No assertion needed for loading state
                }
            }
        }
    }
}