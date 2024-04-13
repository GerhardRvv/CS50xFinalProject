package com.gerhard.cs50x.tweet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gerhard.cs50x.R
import com.gerhard.cs50x.core.component.TwitterCard
import com.gerhard.cs50x.core.utils.TwitterDataTestUtil
import com.gerhard.cs50x.theme.Shapes
import com.gerhard.cs50x.theme.TwitterAppTheme
import com.gerhard.cs50x.tweet.viewmodel.TweetsViewModel
import com.gerhard.cs50x.tweet.viewmodel.UserDetailsUiState

@Composable
fun UserDetailsScreen(viewModel: TweetsViewModel, onBackClicked: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()

    UserDetailsScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
    )
}

@Composable
fun UserDetailsScreenContent(
    uiState: UserDetailsUiState,
    onBackClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(TwitterAppTheme.colors.bg01)
    ) {
        if (uiState.isLoading) {
            LoadingView()
        } else {
            UserDetailsContent(uiState, onBackClicked)
        }
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Color.White)
    }
}

@Composable
fun UserDetailsContent(uiState: UserDetailsUiState, onBackClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(12.dp)
    ) {
        CircularBackButton(onClick = onBackClicked)
        Spacer(modifier = Modifier.height(12.dp))
        UserDetailsHeader(uiState = uiState, header = uiState.user.screenName)
        BannerImageContent(uiState.user.profileBannerUrl)
        Spacer(modifier = Modifier.height(10.dp))
        InformationLabel("Name", uiState.user.name)
        InformationLabel("Screen Name", uiState.user.screenName)
        InformationLabel("Description", uiState.user.description)
        InformationLabel("Followers", uiState.user.followersCount.toString())
        InformationLabel("Joined", uiState.user.createdDate)

        uiState.tweet?.let { tweet ->
            TwitterCard(
                userName = uiState.user.name,
                screenName = uiState.user.screenName,
                date = tweet.createdAt,
                tweetText = tweet.text,
                userProfileImage = uiState.user.profileImageUrl
            )
        }
    }
}


@Composable
fun CircularBackButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_button),
            contentDescription = "Back button",
            modifier = Modifier.size(34.dp),
            tint = TwitterAppTheme.colors.accent03
        )
    }
}

@Composable
private fun BannerImageContent(
    backgroundImage: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backgroundImage)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Product image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(Shapes.small)
                .size(width = 80.dp, height = 200.dp)
        )
    }
}

@Composable
private fun ProfileImageContent(
    profileImage: String?
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(profileImage)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Product image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(CircleShape)
                .size(60.dp)
        )
    }
}

@Composable
private fun InformationLabel(
    fieldName: String,
    fieldValue: String?
) {
    if (!fieldValue.isNullOrEmpty()) {
        ProvideTextStyle(value = TwitterAppTheme.typography.bodySmall) {
            Text(
                text = fieldName,
                color = TwitterAppTheme.colors.accent01,
                fontFamily = FontFamily.SansSerif,
                fontSize = 22.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        ProvideTextStyle(value = TwitterAppTheme.typography.body) {
            Text(
                text = "$fieldValue",
                color = TwitterAppTheme.colors.text01
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(thickness = 1.dp, color = TwitterAppTheme.colors.accent03)
        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Composable
private fun UserDetailsHeader(uiState: UserDetailsUiState, header: String?) {
    header?.let {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ProvideTextStyle(value = TwitterAppTheme.typography.title) {
                Text(
                    text = it,
                    color = TwitterAppTheme.colors.text01,
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp
                )
            }
            ProfileImageContent(profileImage = uiState.user.profileImageUrl)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview(isDarkMode: Boolean = false) {
    TwitterAppTheme(darkTheme = isDarkMode) {
        UserDetailsScreenContent(
            uiState = UserDetailsUiState(
                user = TwitterDataTestUtil.userDetails(),
                tweet = TwitterDataTestUtil.tweetDetails(),
            ),
            onBackClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreviewDark() {
    DefaultPreview(true)
}
