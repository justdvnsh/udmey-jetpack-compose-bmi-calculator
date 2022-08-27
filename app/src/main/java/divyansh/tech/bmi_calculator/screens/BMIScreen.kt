package divyansh.tech.bmi_calculator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import divyansh.tech.bmi_calculator.Indicator.CircularIndicator
import divyansh.tech.bmi_calculator.ui.theme.quickSand

@Composable
fun BMIScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularIndicator(
            indicatorValue = 21.4F,
            modifier = Modifier
            .fillMaxWidth()
            .height(400.dp))
        
        Text(
            text = "You have a BMI of 21.4. This is great. Keep working hard and you'll be there in no time.",
            fontFamily = quickSand,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun showBMIScreen() {
    BMIScreen()
}