package com.gerhard.cs50x.tweet.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gerhard.cs50x.core.api.model.Tweet
import com.gerhard.cs50x.core.api.model.TwitterUser
import com.gerhard.cs50x.core.utils.Resource
import com.gerhard.cs50x.tweet.fragment.UserDetailsFragmentNavArgs
import com.gerhard.cs50x.tweet.usecase.GetTweetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val KEY_ARGS = "args"

@HiltViewModel
class TweetsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTweetsUseCase: GetTweetsUseCase
) : ViewModel() {

    private val userDetails: TwitterUser =
        savedStateHandle.get<UserDetailsFragmentNavArgs>(KEY_ARGS)?.user
            ?: throw IllegalStateException("User details not found in saved state")

    private val _uiState =
        MutableStateFlow(UserDetailsUiState(user = userDetails, isLoading = true))
    val uiState: StateFlow<UserDetailsUiState> = _uiState.asStateFlow()

    init {
        getUserTweets(userId = userDetails.id)
    }

    private fun getUserTweets(userId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            delay(3000)
            when (val response = getTweetsUseCase(userId)) {
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            user = userDetails,
                            tweet = response.data,
                            isLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            errorMessage = "Failed to load tweet",
                            isLoading = false
                        )
                    }
                }

                Resource.Loading -> {}
            }
        }
    }
}

data class UserDetailsUiState(
    val user: TwitterUser,
    val tweet: Tweet? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
