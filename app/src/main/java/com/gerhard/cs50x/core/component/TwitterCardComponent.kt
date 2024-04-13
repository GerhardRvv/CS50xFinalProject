package com.gerhard.cs50x.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gerhard.cs50x.R
import com.gerhard.cs50x.core.utils.TwitterDataTestUtil
import com.gerhard.cs50x.theme.TwitterAppTheme

@Composable
fun TwitterCard(
    userName: String,
    screenName: String,
    date: String,
    tweetText: String,
    userProfileImage: String
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        backgroundColor = TwitterAppTheme.colors.bg02
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userProfileImage)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "Product image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    TextField(text = userName)
                    TextField(
                        type = TwitterAppTheme.typography.bodySmall,
                        text = "@$screenName · $date",
                        color = TwitterAppTheme.colors.text02
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(text = tweetText)
        }
    }
}

@Composable
fun TextField(
    type: TextStyle = TwitterAppTheme.typography.body,
    text: String,
    color: Color = TwitterAppTheme.colors.text01
) {
    ProvideTextStyle(value = type) {
        androidx.compose.material.Text(
            text = text,
            color = color
        )
    }
}

@Preview
@Composable
private fun TwitterCardPreview(isDark: Boolean = false) {
    TwitterAppTheme(darkTheme = isDark) {
        TwitterCard(
            userName = TwitterDataTestUtil.userDetails().name,
            screenName = TwitterDataTestUtil.userDetails().screenName,
            date = TwitterDataTestUtil.tweetDetails().createdAt,
            tweetText = TwitterDataTestUtil.tweetDetails().text,
            userProfileImage = "image"
        )
    }
}

@Preview
@Composable
private fun TwitterCardPreviewDark() {
    TwitterCardPreview(true)
}
