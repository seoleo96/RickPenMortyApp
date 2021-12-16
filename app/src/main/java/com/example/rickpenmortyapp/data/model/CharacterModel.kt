package com.example.rickpenmortyapp.data.model

import com.example.rickpenmortyapp.data.mapper.IRickDataToCacheMapper


sealed class DataModel{
    abstract fun <T> map(mapper: IRickDataToCacheMapper<T>): T
    data class CharacterModel(
        private val created: String,
        private val gender: String,
        private val id: Int,
        private val image: String,
        private val name: String,
        private val species: String,
        private val status: String,
        private val type: String,
        private val url: String,
    ) : DataModel(){
        override fun <T> map(mapper: IRickDataToCacheMapper<T>): T {
            return mapper.map(created,
                gender,
                id,
                image,
                name,
                species,
                status,
                type,
                url,)
        }
    }
}