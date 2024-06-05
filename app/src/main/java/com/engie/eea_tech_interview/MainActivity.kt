package com.engie.eea_tech_interview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.engie.eea_tech_interview.network.api.MovieApiService
import com.engie.eea_tech_interview.model.SearchResult
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private val retrofit: Retrofit by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}