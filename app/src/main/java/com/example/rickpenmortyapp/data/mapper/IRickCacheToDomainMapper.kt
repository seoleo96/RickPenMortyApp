package com.example.rickpenmortyapp.data.mapper

interface IRickCacheToDomainMapper<T> {
    fun map(
        name: String,
        species: String,
        status: String,
        image: String,
    ): T
}