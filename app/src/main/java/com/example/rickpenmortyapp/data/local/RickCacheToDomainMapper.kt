package com.example.rickpenmortyapp.data.local

import com.example.rickpenmortyapp.data.mapper.IRickCacheToDomainMapper
import com.example.rickpenmortyapp.domain.model.CharacterDomainModel

class RickCacheToDomainMapper : IRickCacheToDomainMapper<CharacterDomainModel> {
    override fun map(
        name: String,
        species: String,
        status: String,
        image: String,
    ): CharacterDomainModel {
        return CharacterDomainModel(name, species, status, image)
    }
}