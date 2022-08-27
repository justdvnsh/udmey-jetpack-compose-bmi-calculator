package divyansh.tech.bmi_calculator.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import divyansh.tech.bmi_calculator.HeightPicker.HeightPicker
import divyansh.tech.bmi_calculator.WeightPicker.Slider
import divyansh.tech.bmi_calculator.WeightPicker.rememberSliderState
import divyansh.tech.bmi_calculator.ui.theme.quickSand

@Composable
fun WeightScreen() {
    var weight = rememberSliderState()

    Log.e("WEIGHT STATE -> ", weight.currentValue.toInt().toString())
    Column(
        modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Select Your Weight", fontFamily = quickSand,
            fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Slider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            state = weight,
            currentValueLabel = { value ->
                Text(
                    text = "${(value)}Kg",
                    style = MaterialTheme.typography.h1
                )
            },
            indicatorLabel = { value ->
                if (value % 5 == 0) {
                    Text(
                        text = "$value",
                        style = MaterialTheme.typography.body2,
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun showWeightScreen() {
    WeightScreen()
}