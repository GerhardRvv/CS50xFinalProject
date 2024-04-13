package com.gerhard.cs50x.core.api

import com.gerhard.cs50x.core.api.model.TweetsResponse
import com.gerhard.cs50x.core.api.model.TwitterUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiTwitterDataService {

    @GET("user/details")
    suspend fun getTwitterUser(
        @Query("username") screenName: String
    ): TwitterUserResponse

    @GET("user/tweets")
    suspend fun getUserLastTweet(
        @Query("user_id") userId: Long,
    ): TweetsResponse
}
