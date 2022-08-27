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
import divyansh.tech.bmi_calculator.WeightPicker.WeightScale

@Composable
fun WeightScreen() {
    var weight by remember {
        mutableStateOf(0)
    }

    WeightScale(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp), onWeightSelected = {
            weight = it
        })
}

@Preview(showBackground = true)
@Composable
fun showWeightScreen() {
    WeightScreen()
}