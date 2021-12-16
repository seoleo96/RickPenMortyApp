package com.example.rickpenmortyapp.domain

import com.example.rickpenmortyapp.domain.model.CharacterDomainModel
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

sealed class ResultData {
    data class Success(val data: Flow<List<CharacterDomainModel>>) : ResultData()
    data class Error(val error: Exception) : ResultData()
}