package com.example.rickpenmortyapp.data.net

import com.example.rickpenmortyapp.data.model.DataModel
import retrofit2.Response

interface ICloudDataSource {
    suspend fun fetchCharacters(): Response<List<DataModel.CharacterModel>>
}