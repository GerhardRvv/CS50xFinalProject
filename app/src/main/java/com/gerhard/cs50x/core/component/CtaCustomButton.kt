package com.gerhard.cs50x.core.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gerhard.cs50x.R
import com.gerhard.cs50x.theme.TwitterAppTheme

@Composable
fun CtaCustomButton(
    text: String,
    onUserClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        Divider(color = TwitterAppTheme.colors.accent03)

        Spacer(modifier = Modifier.height(12.dp))

        val buttonsColors = ButtonDefaults.buttonColors(
            backgroundColor = TwitterAppTheme.colors.bg02,
            contentColor = TwitterAppTheme.colors.text01
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            onClick = { onUserClick() },
            enabled = text.isNotEmpty(),
            shape = MaterialTheme.shapes.medium,
            colors = buttonsColors
        ) {
            Text(stringResource(id = R.string.twitter_user_search_button))
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview
@Composable
private fun CtaButtonPreview(isDark: Boolean = false) {
    TwitterAppTheme(darkTheme = isDark) {
        CtaCustomButton(text = "Search") {
        }
    }
}

@Preview
@Composable
private fun CtaButtonPreviewDark() {
    CtaButtonPreview(true)
}
