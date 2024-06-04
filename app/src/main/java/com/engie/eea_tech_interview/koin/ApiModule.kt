package com.engie.eea_tech_interview.koin

import com.engie.eea_tech_interview.network.api.MovieApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    single {
        get<Retrofit>().create(MovieApiService::class.java)
    }
}