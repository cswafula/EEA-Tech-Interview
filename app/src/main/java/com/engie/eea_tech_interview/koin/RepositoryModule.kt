package com.engie.eea_tech_interview.koin

import com.engie.eea_tech_interview.network.repositories.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single {
        MoviesRepository(get())
    }
}