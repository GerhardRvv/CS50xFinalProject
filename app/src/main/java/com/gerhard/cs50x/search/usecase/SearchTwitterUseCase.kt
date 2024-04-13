package com.gerhard.cs50x.search.usecase

import com.gerhard.cs50x.core.api.model.TwitterUser
import com.gerhard.cs50x.core.utils.Resource
import com.gerhard.cs50x.search.repository.UserSearchRepository
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class UserSearchUseCase @Inject constructor(
    private val repository: UserSearchRepository
) {
    suspend operator fun invoke(screenName: String): Resource<TwitterUser> {
        return repository.getTwitterUser(screenName).last()
    }
}
