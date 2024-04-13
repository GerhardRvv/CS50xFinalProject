package com.gerhard.cs50x.core.api.model

data class TwitterUserResponse(
    val user_id: Long,
    val username: String,
    val name: String,
    val description: String,
    val follower_count: Int,
    val following_count: Int,
    val creation_date: String,
    val profile_pic_url: String?,
    val profile_banner_url: String?
)
