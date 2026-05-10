package com.example.pagination_3_practices.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagination_3_practices.data.model.Character
import com.example.pagination_3_practices.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class CharacterViewModel : ViewModel() {
    private val repository = CharacterRepository()

    val characters: Flow<PagingData<Character>> = repository.getCharacters()
}
