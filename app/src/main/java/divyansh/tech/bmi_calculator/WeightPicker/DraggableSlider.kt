package divyansh.tech.bmi_calculator.WeightPicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.*

//Math
//r = √(x2 + y2)
//x = r * cos(phi) -> arcos(x/r) = phi
//y = r * sin (phi)
//r - radius
//phi - angle

@Composable
fun RoundSlider(modifier: Modifier = Modifier) {
    var dragPosition by remember {
        mutableStateOf(Offset.Zero)
    }
    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = {
                        dragPosition = it
                    },
                    onDrag = {_, dragDistance ->
                        dragPosition += dragDistance
                    }
                )
            }
    ) {
        val (indicatorX, indicatorY) = calculateIndicatorPosition(dragPosition)

        translate(indicatorX, indicatorY) {
            drawCircle(
                color = Color.Magenta,
                radius = indicatorCircleRadius(),
                style = Fill
            )
        }

        drawCircle(
            color = Color.Magenta.copy(alpha = 0.4f),
            radius = outerCircleRadius(),
            style = Stroke(width = 6.dp.toPx())
        )
    }
}

private fun DrawScope.calculateIndicatorPosition(dragPosition: Offset): Offset {
    val dragXOnCanvas = dragPosition.x - horizontalCenter
    val dragYOnCanvas = dragPosition.y - verticalCenter
    val radius = radiusForPoint(dragXOnCanvas, dragYOnCanvas)
    val angle = acos(dragXOnCanvas / radius)
    val adjustedAngle = if (dragYOnCanvas < 0) angle * -1 else angle
    val xOnCircle = outerCircleRadius() * cos(adjustedAngle)
    val yOnCircle = outerCircleRadius() * sin(adjustedAngle)
    return Offset(xOnCircle, yOnCircle)
}

fun radiusForPoint(x: Float, y: Float): Float {
    return sqrt(x.pow(2) + y.pow(2))
}

fun DrawScope.indicatorCircleRadius(): Float {
    return outerCircleRadius() / 12
}

private fun DrawScope.outerCircleRadius(): Float {
    return (horizontalCenter).coerceAtMost(verticalCenter)
}

private val DrawScope.horizontalCenter get() = size.width / 2
private val DrawScope.verticalCenter get() = size.height / 2