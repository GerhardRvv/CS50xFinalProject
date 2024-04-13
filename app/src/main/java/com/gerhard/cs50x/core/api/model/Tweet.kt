package com.gerhard.cs50x.core.api.model

data class Tweet(
    val text: String,
    val createdAt: String,
    val retweetCount: Int,
    val favoriteCount: Int
)
