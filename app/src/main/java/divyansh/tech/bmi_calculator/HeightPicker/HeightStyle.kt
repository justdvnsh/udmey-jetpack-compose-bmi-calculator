package divyansh.tech.bmi_calculator.HeightPicker

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class HeightStyle(
    val heightWidth: Dp = 100.dp,
    val tenStepCountLength: Dp = 10.dp,
    val fiveStepCountLength: Dp = 25.dp,
    val lineColor: Color = Color.LightGray,
    val scaleGap: Dp = 8.dp
)