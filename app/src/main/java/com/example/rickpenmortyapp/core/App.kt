package com.example.rickpenmortyapp.core

import android.app.Application
import com.example.rickpenmortyapp.data.local.CacheDataSource
import com.example.rickpenmortyapp.data.local.RickCacheToDomainMapper
import com.example.rickpenmortyapp.data.net.*
import com.example.rickpenmortyapp.data.repository.Repository
import com.example.rickpenmortyapp.domain.usecase.FetchAllDataUseCase
import com.example.rickpenmortyapp.ui.RickCommunication
import com.example.rickpenmortyapp.ui.home.HomeViewModel
import retrofit2.Retrofit

class App : Application() {
    lateinit var homeViewModel: HomeViewModel
    override fun onCreate() {
        super.onCreate()

        val retrofit: Retrofit = RetrofitInstance.retrofit()
        val service: ApiService = RetrofitInstance.service(retrofit, ApiService::class.java)
        val cloudDataSource = CloudDataSource(service)
         val dataToCacheModelMapper = RickDataToCacheMapper()
         val cacheDataSource = CacheDataSource(this)
         val cacheToDomainModelMapper = RickCacheToDomainMapper()
        val repository = Repository(
            cloudDataSource,
            dataToCacheModelMapper,
            cacheDataSource,
            cacheToDomainModelMapper,
            this
        )
        val fetchAllDataUseCase = FetchAllDataUseCase(repository)
        val rickCommunication = RickCommunication()
        homeViewModel = HomeViewModel(fetchAllDataUseCase, rickCommunication)
    }
}