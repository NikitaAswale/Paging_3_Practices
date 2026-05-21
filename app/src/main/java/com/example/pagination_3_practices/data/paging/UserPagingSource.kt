package com.example.pagination_3_practices.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagination_3_practices.data.api.CharacterApiService
import com.example.pagination_3_practices.data.model.Character

class CharacterPagingSource(
    private val apiService: CharacterApiService
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1 // page = 3
            val response = apiService.getCharacters(page) // page = 3

            LoadResult.Page(
                data = response.results,
                prevKey = if (response.info.prev == null) null else page - 1,
                nextKey = if (response.info.next == null) null else page + 1
            )

            // next key = 3, prevKey = 1
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
