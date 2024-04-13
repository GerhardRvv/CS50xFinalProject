package com.gerhard.cs50x.search.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerhard.cs50x.R
import com.gerhard.cs50x.core.component.CustomOutlinedTextField
import com.gerhard.cs50x.search.viewmodel.SearchUserViewModel
import com.gerhard.cs50x.search.viewmodel.TwitterUserSearchUiState
import com.gerhard.cs50x.theme.Shapes
import com.gerhard.cs50x.theme.TwitterAppTheme

@Composable
fun TwitterUserSearchScreen(viewModel: SearchUserViewModel) {
    val uiState = viewModel.uiState
    val state = uiState.collectAsState()

    val recentSearches by viewModel.recentSearches.collectAsState()

    TwitterUserSearchContent(
        uiState = state.value,
        recentSearches = recentSearches,
        onSearchUserClick = { userName -> viewModel.getUser(userName) },
    )
}

@Composable
fun TwitterUserSearchContent(
    uiState: TwitterUserSearchUiState,
    onSearchUserClick: (userName: String) -> Unit,
    recentSearches: List<String>,
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TwitterAppTheme.colors.bg01)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = stringResource(id = R.string.twitter_search_screen_body),
            style = TwitterAppTheme.typography.h6,
            color = TwitterAppTheme.colors.accent03,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(32.dp))

        if (uiState.error) {
            Text(
                text = uiState.errorMsg,
                style = TwitterAppTheme.typography.body2,
                color = TwitterAppTheme.colors.accent01,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomOutlinedTextField { text = it.trim() }

            Button(
                modifier = Modifier
                    .width(100.dp),
                onClick = { onSearchUserClick(text) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TwitterAppTheme.colors.accent02
                )
            ) {
                Icon(
                    modifier = Modifier.size(44.dp),
                    imageVector = Icons.Filled.Search, contentDescription = "Search",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recent Searches",
            style = MaterialTheme.typography.h4,
            color = TwitterAppTheme.colors.text01
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier
                .clip(shape = Shapes.small)
                .background(color = TwitterAppTheme.colors.bg03)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (recentSearches.isEmpty()) {
                item {
                    EmptySearchPlaceholder() // Call a separate Composable for the empty placeholder
                }
            } else {
                items(recentSearches) { searchTerm ->
                    Spacer(modifier = Modifier.height(12.dp))

                    Card(modifier = Modifier.padding(horizontal = 8.dp)) {
                        Text(
                            text = searchTerm,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSearchUserClick(searchTerm) }
                                .padding(16.dp),
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptySearchPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "No recent searches yet",
            style = MaterialTheme.typography.h5,
            color = TwitterAppTheme.colors.text01.copy(alpha = 0.3f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserSearchPreview(isDarkMode: Boolean = false) {
    TwitterAppTheme(darkTheme = isDarkMode) {
        TwitterUserSearchContent(
            uiState = TwitterUserSearchUiState(error = true),
            onSearchUserClick = {},
            recentSearches = listOf("text", "text2", "text3")
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserSearchPreviewDark() {
    UserSearchPreview(true)
}

@Preview(showBackground = true)
@Composable
private fun UserSearchErrorPreview(isDarkMode: Boolean = false) {
    TwitterAppTheme(darkTheme = isDarkMode) {
        TwitterUserSearchContent(
            uiState = TwitterUserSearchUiState(error = false),
            onSearchUserClick = {},
            recentSearches = listOf()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserSearchErrorPreviewDark() {
    UserSearchErrorPreview(true)
}
