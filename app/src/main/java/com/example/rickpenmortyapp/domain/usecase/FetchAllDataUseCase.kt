package com.example.rickpenmortyapp.domain.usecase

import com.example.rickpenmortyapp.domain.ResultData
import com.example.rickpenmortyapp.domain.repository.IRepository

class FetchAllDataUseCase(
    private val repository: IRepository,
) : IFetchAllDataUseCase {
    override suspend fun fetchAllData(): ResultData {
        return repository.fetchCharacters()
    }
}