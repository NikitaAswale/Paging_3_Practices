package com.example.pagination_3_practices.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagination_3_practices.data.api.CharacterApiService
import com.example.pagination_3_practices.data.api.RetrofitClient
import com.example.pagination_3_practices.data.model.Character
import com.example.pagination_3_practices.data.paging.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.http.POST

class CharacterRepository {
    private val apiService = RetrofitClient.apiService

    /*

    apiService - BAse url - "BASE_URL"
                 Convert tyo Data class
     */

    fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(apiService)
            }
        ).flow
    }
}
