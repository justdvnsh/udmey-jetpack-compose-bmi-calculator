package divyansh.tech.bmi_calculator.StepProgress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StepsProgressBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
fun RowScope.Step(modifier: Modifier = Modifier, onClick: () -> Unit) {

    var isComplete by remember {
        mutableStateOf(false)
    }
    var isCurrent by remember {
        mutableStateOf(true)
    }

    val color = if (isComplete || isCurrent) MaterialTheme.colors.primary else Color.LightGray
    val innerCircleColor = if (isComplete) MaterialTheme.colors.primary else Color.LightGray

    Box(modifier =
    modifier
        .weight(1f)
        .clickable {
            isComplete = !isComplete
            isCurrent = !isCurrent
            onClick()
        }
    ) {

        //Line
        Divider(
            modifier = Modifier.align(Alignment.CenterStart),
            color = color,
            thickness = 2.dp
        )

        //Circle
        Canvas(modifier = Modifier
            .size(15.dp)
            .align(Alignment.CenterEnd)
            .border(
                shape = CircleShape,
                width = 2.dp,
                color = color
            ),
            onDraw = {
                drawCircle(color = innerCircleColor)
            }
        )
    }
}

@Preview
@Composable
fun StepsProgressBarPreview() {
    var currentStep by remember { mutableStateOf(1) }

}