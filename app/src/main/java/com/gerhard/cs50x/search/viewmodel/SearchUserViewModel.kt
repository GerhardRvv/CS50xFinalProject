package com.gerhard.cs50x.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerhard.cs50x.core.api.model.TwitterUser
import com.gerhard.cs50x.core.utils.Resource
import com.gerhard.cs50x.core.utils.SingleLiveEvent
import com.gerhard.cs50x.search.usecase.UserSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUserViewModel @Inject constructor(
    private val getUserUseCase: UserSearchUseCase
) : ViewModel() {

    val navigationEvent = SingleLiveEvent<TwitterUser?>()

    private val _uiState = MutableStateFlow(TwitterUserSearchUiState())
    val uiState: StateFlow<TwitterUserSearchUiState> = _uiState.asStateFlow()

    private val _recentSearches = MutableStateFlow(listOf<String>())
    val recentSearches: StateFlow<List<String>> = _recentSearches.asStateFlow()


    fun getUser(userName: String) {
        _uiState.update { it.copy(loading = true, error = false, errorMsg = "") }
        viewModelScope.launch {
            when (val response = getUserUseCase(userName)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(loading = false, error = false, errorMsg = "") }
                    addSearchToList(userName)
                    navigationEvent.value = response.data
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            error = true, errorMsg = response.exception.message
                                ?: "An unknown error occurred"
                        )
                    }
                }

                Resource.Loading -> {
                    _uiState.update {
                        it.copy(loading = true, error = false, errorMsg = "")
                    }
                }
            }
        }
    }

    private fun addSearchToList(userName: String) {
        if (!_recentSearches.value.contains(userName)) {
            _recentSearches.value = listOf(userName) + _recentSearches.value.take(9)
        }
    }
}

data class TwitterUserSearchUiState(
    val userName: String? = null,
    val loading: Boolean = false,
    val error: Boolean = false,
    val errorMsg: String = ""
)
