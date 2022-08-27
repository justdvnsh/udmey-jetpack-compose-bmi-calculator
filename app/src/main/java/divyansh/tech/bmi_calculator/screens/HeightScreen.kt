package divyansh.tech.bmi_calculator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import divyansh.tech.bmi_calculator.HeightPicker.HeightPicker

@Composable
fun HeightScreen() {

    var height by remember {
        mutableStateOf(0)
    }

    HeightPicker(modifier = Modifier
        .fillMaxHeight()
        .width(140.dp), onHeightPicked = {
        height = it
    })
}

@Preview(showBackground = true)
@Composable
fun showHeightScreen() {
    HeightScreen()
}