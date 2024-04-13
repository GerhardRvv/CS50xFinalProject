package com.gerhard.cs50x.tweet.usecase

import com.gerhard.cs50x.core.utils.Resource
import com.gerhard.cs50x.core.api.model.Tweet
import com.gerhard.cs50x.tweet.repository.TwitterTweetRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.last

class GetTweetsUseCase @Inject constructor(
    private val repository: TwitterTweetRepository,
) {
    suspend operator fun invoke(userId: Long): Resource<Tweet> {
        return repository.getLastTweetFromUser(userId).last()
    }
}
