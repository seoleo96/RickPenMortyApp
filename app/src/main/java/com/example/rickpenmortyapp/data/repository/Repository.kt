package com.example.rickpenmortyapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.example.rickpenmortyapp.data.local.CharacterCache
import com.example.rickpenmortyapp.data.local.ICacheDataSource
import com.example.rickpenmortyapp.data.mapper.IRickCacheToDomainMapper
import com.example.rickpenmortyapp.data.mapper.IRickDataToCacheMapper
import com.example.rickpenmortyapp.data.model.CharacterListModel
import com.example.rickpenmortyapp.data.model.DataModel
import com.example.rickpenmortyapp.data.net.ICloudDataSource
import com.example.rickpenmortyapp.domain.ResultData
import com.example.rickpenmortyapp.domain.model.CharacterDomainModel
import com.example.rickpenmortyapp.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import java.net.UnknownHostException

class Repository(
    private val cloudDataSource: ICloudDataSource,
    private val dataToCacheModelMapper: IRickDataToCacheMapper<CharacterCache.CharacterCacheModel>,
    private val cacheDataSource: ICacheDataSource,
    private val cacheToDomainModelMapper: IRickCacheToDomainMapper<CharacterDomainModel>,
    private val context: Context,
) : IRepository {
    override suspend fun fetchCharacters(): ResultData {
        try {
            return if (checkInternet(context)) {
                val response: Response<List<DataModel.CharacterModel>> = cloudDataSource.fetchCharacters()
                val data: List<DataModel.CharacterModel> = response.body()!!
                val cacheData: List<CharacterCache.CharacterCacheModel> = data.map {
                    it.map(dataToCacheModelMapper)
                }
                cacheDataSource.insertQuotes(cacheData)
                ResultData.Success(fetchAllFromCache())
            } else {
                ResultData.Success(fetchAllFromCache())
            }
        } catch (e: Exception) {
            return ResultData.Error(e)
        }
    }

    private fun fetchAllFromCache(): Flow<List<CharacterDomainModel>> {
        return cacheDataSource.fetchAll().map { list ->
            list.map {
                it.map(cacheToDomainModelMapper)
            }
        }
    }

    private fun checkInternet(context: Context): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}