package com.pdm.fipr.mini_app_post.screens.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.fipr.mini_app_post.data.repositories.jsonRepository.JsonApiRepository
import com.pdm.fipr.mini_app_post.data.repositories.jsonRepository.JsonRepository
import com.pdm.fipr.mini_app_post.model.Comment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel : ViewModel()  {
    private val repository : JsonRepository = JsonApiRepository()
    private val _uiState = MutableStateFlow(PostUiState(isLoading = false))
    val uiState = _uiState.asStateFlow()


    fun addComment(comment: Comment) {
        viewModelScope.launch {
            val newComment : Comment = repository.postComment(comment)
            _uiState.update { state ->
                state.copy(
                    list = state.list + newComment,
                    isLoading = false
                )
            }
        }
    }


}