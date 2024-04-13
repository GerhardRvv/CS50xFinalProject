package com.gerhard.cs50x.core.mapper

import com.gerhard.cs50x.core.api.model.Tweet
import com.gerhard.cs50x.core.api.model.TweetResponse
import com.gerhard.cs50x.core.utils.toFormattedDate
import javax.inject.Inject

class TweetMapper @Inject constructor() : Mapper<TweetResponse, Tweet> {
    override fun map(input: TweetResponse): Tweet {
        return Tweet(
            text = input.text,
            createdAt = input.creation_date.toFormattedDate(),
            retweetCount = input.retweet_count,
            favoriteCount = input.favorite_count
        )
    }
}
