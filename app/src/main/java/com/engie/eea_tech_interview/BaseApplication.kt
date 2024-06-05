package com.engie.eea_tech_interview

import androidx.multidex.MultiDexApplication
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.engie.eea_tech_interview.koin.apiModule
import com.engie.eea_tech_interview.koin.networkModule
import com.engie.eea_tech_interview.koin.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

open class BaseApplication: MultiDexApplication(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(getDependencyModules())
        }
    }

    open fun getDependencyModules(): List<Module> {
        return listOf(
            networkModule,
            apiModule,
            repositoryModule,
        )
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache{
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .build()
    }
}
