package com.example.rickpenmortyapp.data.local


import kotlinx.coroutines.flow.Flow


interface ICacheDataSource {

    fun fetchAll(): Flow<List<CharacterCache.CharacterCacheModel>>
//    fun fetchQuotesCheck(): List<QuoteCacheModel>
    suspend fun insertQuotes(quotes: List<CharacterCache.CharacterCacheModel>)
//    suspend fun fetchAuthors(): List<QuoteCacheModel>
//    suspend fun fetchAuthorsByQuery(query : String): List<QuoteCacheModel>
//    suspend fun updateQuote(isCheck: Boolean, author: String)
//    fun fetchFavourites() : Flow<List<QuoteCacheModel>>
//    suspend fun toSaveAndDelete(toSave: Boolean, text: String) : Int
}