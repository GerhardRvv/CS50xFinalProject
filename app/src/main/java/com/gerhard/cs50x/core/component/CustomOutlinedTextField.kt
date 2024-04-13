package com.gerhard.cs50x.core.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerhard.cs50x.R
import com.gerhard.cs50x.theme.TwitterAppTheme

@Composable
fun CustomOutlinedTextField(
    onTextChanged: (inputText: String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    val customTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = TwitterAppTheme.colors.accent01,
        unfocusedBorderColor = TwitterAppTheme.colors.accent03,
        textColor = TwitterAppTheme.colors.text01,
        cursorColor = TwitterAppTheme.colors.text01,
        focusedLabelColor = TwitterAppTheme.colors.text01,
        unfocusedLabelColor = TwitterAppTheme.colors.text01,
        placeholderColor = TwitterAppTheme.colors.text01
    )

    val customHintTextStyle = TextStyle(
        fontSize = 22.sp,
        color = TwitterAppTheme.colors.text01
    )

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onTextChanged(text)
        },
        label = {
            Text(
                text = stringResource(id = R.string.input_field_label),
                fontSize = 22.sp,
                style = customHintTextStyle
            )
        },
        modifier = Modifier
            .widthIn(max = 300.dp)
            .padding(horizontal = 16.dp),
        isError = false,
        singleLine = true,
        colors = customTextFieldColors
    )
}
