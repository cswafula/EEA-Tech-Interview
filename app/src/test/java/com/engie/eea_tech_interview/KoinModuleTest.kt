package com.engie.eea_tech_interview

import android.content.Context
import com.engie.eea_tech_interview.network.api.MovieApiService
import com.engie.eea_tech_interview.network.repositories.MoviesRepository
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.Test

@RunWith(MockitoJUnitRunner::class)
class KoinModuleTest: KoinTest {
    private val apiService: MovieApiService by inject()
    private val repository: MoviesRepository by inject()

    @get:Rule
    val mockProvider = MockProviderRule.create {
        Mockito.mock(it.java)
    }

    @Before
    fun setUp() {
        startKoin {
            androidContext(Mockito.mock(Context::class.java))
        }
    }

    @After
    fun tearDown(){
        stopKoin()
    }

    @Test
    fun testApiService() {
        declareMock<MovieApiService>()
        Assert.assertNotNull(apiService)
    }

    @Test
    fun testRepository() {
        declareMock<MoviesRepository>()
        Assert.assertNotNull(repository)
    }
}