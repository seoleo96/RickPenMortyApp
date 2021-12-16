package com.example.rickpenmortyapp.domain.usecase

import com.example.rickpenmortyapp.domain.ResultData

interface IFetchAllDataUseCase {
    suspend fun fetchAllData(): ResultData
}