package com.example.rickpenmortyapp.domain.repository

import com.example.rickpenmortyapp.domain.ResultData

interface IRepository {
    suspend fun fetchCharacters() : ResultData
}