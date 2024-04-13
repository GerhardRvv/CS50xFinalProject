package com.gerhard.cs50x.core.mapper

import com.gerhard.cs50x.core.api.model.TwitterUser
import com.gerhard.cs50x.core.api.model.TwitterUserResponse
import com.gerhard.cs50x.core.utils.toFormattedDate
import javax.inject.Inject

class TwitterUserMapper @Inject constructor() : Mapper<TwitterUserResponse, TwitterUser> {
    override fun map(input: TwitterUserResponse): TwitterUser {
        return TwitterUser(
            id = input.user_id,
            name = input.username,
            screenName = input.name,
            description = input.description,
            followersCount = input.follower_count,
            friendsCount = input.following_count,
            createdDate = input.creation_date.toFormattedDate(),
            profileImageUrl = input.profile_pic_url ?: "",
            profileBannerUrl = input.profile_banner_url ?: ""
        )
    }
}
