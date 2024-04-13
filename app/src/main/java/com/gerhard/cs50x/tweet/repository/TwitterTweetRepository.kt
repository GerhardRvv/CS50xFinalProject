package com.gerhard.cs50x.tweet.repository

import com.gerhard.cs50x.core.api.ApiTwitterDataService
import com.gerhard.cs50x.core.api.model.Tweet
import com.gerhard.cs50x.core.mapper.TweetMapper
import com.gerhard.cs50x.core.utils.Resource
import com.gerhard.cs50x.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TwitterTweetRepository @Inject constructor(
    private val service: ApiTwitterDataService,
    private val tweetMapper: TweetMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getLastTweetFromUser(userId: Long): Flow<Resource<Tweet>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.getUserLastTweet(userId)
            val mapResponse = tweetMapper.map(response.results.first())
            emit(Resource.Success(mapResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(Throwable("Network error: ${e.message}")))
        } catch (e: IOException) {
            emit(Resource.Error(Throwable("Network error: Check your connection")))
        } catch (e: Exception) {
            emit(Resource.Error(Throwable("Unknown error occurred")))
        }
    }.flowOn(dispatcher)
}
