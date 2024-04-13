package com.gerhard.cs50x.core.api.model

data class TweetsResponse(
    val results: List<TweetResponse>
)

data class TweetResponse(
    val text: String,
    val creation_date: String,
    val retweet_count: Int,
    val favorite_count: Int
)
