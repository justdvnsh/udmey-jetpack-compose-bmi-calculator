package divyansh.tech.bmi_calculator.WeightPicker

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class WeightStyle(
    val WeightWidth: Dp = 150.dp,
    val tenStepCountLength: Dp = 5.dp,
    val fiveStepCountLength: Dp = 25.dp,
    val normalStepCountLength: Dp = 45.dp,
    val lineColor: Color = Color.Black,
    val scaleGap: Dp = 8.dp
)