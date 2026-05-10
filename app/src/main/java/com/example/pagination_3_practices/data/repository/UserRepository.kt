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
    private val apiService: CharacterApiService = RetrofitClient.retrofit.create(CharacterApiService::class.java)

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

    suspend fun getPosts() : List<Post> {
        try {
            return apiService.getCharacters()
        } catch (e : Exception) {
            return emptyList()
        }
    }
}
