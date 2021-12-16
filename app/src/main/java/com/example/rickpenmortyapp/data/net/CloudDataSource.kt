package com.example.rickpenmortyapp.data.net


import android.util.Log
import com.example.rickpenmortyapp.data.model.CharacterListModel
import com.example.rickpenmortyapp.data.model.DataModel
import retrofit2.Response

class CloudDataSource(
    private val apiService: ApiService
) : ICloudDataSource {
    override suspend fun fetchCharacters(): Response<List<DataModel.CharacterModel>> {
        val array: List<Int> = (1..826).toList()
        Log.d("array", array.joinToString(prefix = "", postfix = "", separator = ","))
        return apiService.fetchCharacters()
    }
}