package com.github.ephelsa.loginpagedribble.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    textFieldType: KeyboardType = KeyboardType.Text,
    hint: String,
    value: String = "",
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = textFieldType),
        placeholder = { Text(text = hint) },
        shape = RoundedCornerShape(12.dp)
    )
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun LoginTextFieldPreview() {
    LoginTextField(hint = "Email", onValueChange = { println(it) })
}