package divyansh.tech.bmi_calculator.HeightPicker

import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import kotlin.math.abs
import kotlin.math.roundToInt

enum class HeightScales {
    FIVESTEPS, TENSTEPS, NORMAL
}

@Composable
fun HeightPicker(
    modifier: Modifier,
    style: HeightStyle = HeightStyle(),
    minHeightValue: Int = 40,
    maxHeightValue: Int = 220,
    initialHeight: Int = 110,
    onHeightPicked: (Int) -> Unit
) {

    var targetDistant by remember {
        mutableStateOf(0f)
    }

    var startDragPoint by remember {
        mutableStateOf(0f)
    }

    var oldDragPoint by remember {
        mutableStateOf(0f)
    }

    var selectedHeight by remember {
        mutableStateOf(0)
    }

    BoxWithConstraints(modifier = modifier) {
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = 0.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, androidx.compose.ui.graphics.Color.LightGray)
        ) {
            Canvas(modifier =
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            startDragPoint = it.y
                        },
                        onDragEnd = {
                            oldDragPoint = targetDistant
                        }
                    ) { change, dragAmount ->
                        val newDistance = oldDragPoint + (change.position.y - startDragPoint)
                        targetDistant = newDistance.coerceIn(
                            minimumValue = (initialHeight * 18 - maxHeightValue * 18).toFloat(),
                            maximumValue = (initialHeight * 18 - minHeightValue * 18).toFloat()
                        )
                    }
                }
            ) {

                val middlePoint = Offset(
                    x = maxWidth.toPx() / 2f,
                    y = maxHeight.toPx() / 2f
                )

                for (height in minHeightValue..maxHeightValue) {
                    if ((height % 10 == 0) || (height % 5 == 0)) {
                        val degreeLineScaleY =
                            middlePoint.y + (18 * (height - initialHeight.toFloat()) + targetDistant)
                        val lineType = when {
                            height % 10 == 0 -> HeightScales.TENSTEPS
                            height % 5 == 0 -> HeightScales.FIVESTEPS
                            else -> HeightScales.NORMAL
                        }

                        val lineLength = when(lineType) {
                            HeightScales.TENSTEPS -> style.tenStepCountLength.toPx()
                            HeightScales.FIVESTEPS -> style.fiveStepCountLength.toPx()
                            else -> 10.dp.toPx()
                        }

                        val start = Offset(
                            x = 0f + 10.dp.toPx(),
                            y = degreeLineScaleY
                        )

                        val end = Offset(
                            x = ((maxWidth / 2f).toPx() - lineLength),
                            y = degreeLineScaleY
                        )

                        drawLine(
                            start = start,
                            end = end,
                            color = style.lineColor,
                            strokeWidth = 1.dp.toPx()
                        )

                        if (abs(middlePoint.y - degreeLineScaleY.roundToInt()) < 5) {
                            selectedHeight = height
                            onHeightPicked(selectedHeight)
                        }

                        if (height % 20 == 0) {
                            drawContext.canvas.nativeCanvas.apply {
                                drawText(
                                    abs(height).toString(),
                                    maxWidth.toPx() / 2f + 18,
                                    degreeLineScaleY + 18,
                                    Paint().apply {
                                        textSize = if (height == selectedHeight) 24.sp.toPx() else 14.sp.toPx()
                                        color = android.graphics.Color.BLACK
                                        textAlign = Paint.Align.CENTER
                                        typeface = Typeface.DEFAULT_BOLD
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowHeightPicker() {
    HeightPicker(modifier = Modifier
        .fillMaxHeight()
        .width(400.dp), onHeightPicked = {})
}