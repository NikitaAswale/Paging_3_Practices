package com.example.pagination_3_practices.data.api

import com.example.pagination_3_practices.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiService {
    
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponse
}
