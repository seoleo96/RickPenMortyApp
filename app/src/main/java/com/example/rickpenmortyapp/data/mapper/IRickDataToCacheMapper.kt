package com.example.rickpenmortyapp.data.mapper

interface IRickDataToCacheMapper<T> {
    fun map(
        created: String,
        gender: String,
        id: Int,
        image: String,
        name: String,
        species: String,
        status: String,
        type: String,
        url: String,
    ): T
}