package com.example.rickpenmortyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickpenmortyapp.data.mapper.IRickCacheToDomainMapper

sealed class CharacterCache{

    abstract fun <T> map(mapper: IRickCacheToDomainMapper<T>): T

    @Entity(tableName = "rick")
    data class CharacterCacheModel(
        val created: String,
        val gender: String,
        @PrimaryKey
        val id: Int,
        val image: String,
        val name: String,
        val species: String,
        val status: String,
        val type: String,
        val url: String,
    ) : CharacterCache(){
        override fun <T> map(mapper: IRickCacheToDomainMapper<T>): T {
            return mapper.map(name, species, status, image)
        }
    }
}

