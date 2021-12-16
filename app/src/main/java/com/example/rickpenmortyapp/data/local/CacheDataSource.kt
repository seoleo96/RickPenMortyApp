package com.example.rickpenmortyapp.data.local

import android.content.Context
import kotlinx.coroutines.flow.Flow


class CacheDataSource(
    context: Context,
) : ICacheDataSource {

    private val dao = RickDB.getDatabase(context).rickDao()
    override fun fetchAll(): Flow<List<CharacterCache.CharacterCacheModel>> {
        return dao.fetchAll()
    }

    override suspend fun insertQuotes(data: List<CharacterCache.CharacterCacheModel>) {
        dao.deleteAll()
        dao.insertAll(data)
    }
}