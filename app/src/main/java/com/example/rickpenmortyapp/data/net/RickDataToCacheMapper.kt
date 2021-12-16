package com.example.rickpenmortyapp.data.net

import com.example.rickpenmortyapp.data.local.CharacterCache
import com.example.rickpenmortyapp.data.mapper.IRickDataToCacheMapper

class RickDataToCacheMapper : IRickDataToCacheMapper<CharacterCache.CharacterCacheModel> {
    override fun map(
        created: String,
        gender: String,
        id: Int,
        image: String,
        name: String,
        species: String,
        status: String,
        type: String,
        url: String,
    ): CharacterCache.CharacterCacheModel {
        return CharacterCache.CharacterCacheModel(
            created,
            gender,
            id,
            image,
            name,
            species,
            status,
            type,
            url,
        )
    }
}