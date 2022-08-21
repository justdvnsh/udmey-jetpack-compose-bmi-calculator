package divyansh.tech.bmi_calculator.WeightPicker

import android.graphics.*
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.*

enum class WeightLines {
    FIVESTEP, TENSTEP, NORMAL
}

@Composable
fun WeightScale(
    modifier: Modifier,
    onWeightSelected: (Int) -> Unit,
    minWeight: Int = 10,
    maxWeight: Int = 250,
    initialWeight: Int = 70,
    style: WeightStyle = WeightStyle()
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

    var selectedWeight by remember {
        mutableStateOf(0)
    }

    BoxWithConstraints(modifier = modifier) {
        Card(
            modifier = Modifier.fillMaxSize(),
            elevation = 6.dp,
            backgroundColor = androidx.compose.ui.graphics.Color.White,
            shape = RoundedCornerShape(10.dp)
        ) {
            Canvas(modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {
                            startDragPoint = it.x
                        },
                        onDragEnd = {
                            oldDragPoint = targetDistant
                        }
                    ) { change, _ ->
                        val newDistance = oldDragPoint + (change.position.x - startDragPoint)
                        targetDistant = newDistance.coerceIn(
                            minimumValue = (initialWeight * 36 - maxWeight * 36).toFloat(),
                            maximumValue = (initialWeight * 36 - minWeight * 36).toFloat()
                        )
                    }
                }
            ) {

                val middlePoint = Offset(x = maxWidth.toPx() / 2f, y = maxHeight.toPx() / 2f)

                drawContext.canvas.nativeCanvas.apply {

                    for (weight in minWeight..maxWeight) {
                        val degreeLineScaleX =
                            middlePoint.x + (36 * (weight - initialWeight.toFloat()) + targetDistant)
                        val lineType = when {
                            weight % 10 == 0 -> WeightLines.TENSTEP
                            weight % 5 == 0 -> WeightLines.FIVESTEP
                            else -> WeightLines.NORMAL
                        }

                        val lineColor = when (lineType) {
                            WeightLines.TENSTEP -> androidx.compose.ui.graphics.Color.Black
                            WeightLines.FIVESTEP -> androidx.compose.ui.graphics.Color.DarkGray
                            else -> androidx.compose.ui.graphics.Color.LightGray
                        }

                        val lineLength = when (lineType) {
                            WeightLines.TENSTEP -> style.tenStepCountLength.toPx()
                            WeightLines.FIVESTEP -> style.fiveStepCountLength.toPx()
                            else -> style.normalStepCountLength.toPx()
                        }

                        val start = Offset(
                            x = degreeLineScaleX,
                            y = middlePoint.y + lineLength
                        )

                        val end = Offset(
                            x = degreeLineScaleX,
                            y = middlePoint.y + style.WeightWidth.toPx() / 2 - 10.dp.toPx()
                        )

                        drawLine(
                            start = start,
                            end = end,
                            strokeWidth = 1.dp.toPx(),
                            color = lineColor
                        )

                        if (abs(middlePoint.x - degreeLineScaleX.roundToInt()) < 5) {
                            selectedWeight = weight
                            onWeightSelected(selectedWeight)
                        }

                        if (lineType == WeightLines.TENSTEP) {
                            val textBound = Rect()
                            Paint().getTextBounds(
                                abs(weight).toString(),
                                0,
                                weight.toString().length,
                                textBound
                            )

                            drawText(
                                abs(weight).toString(),
                                (degreeLineScaleX) - textBound.width() / 2,
                                middlePoint.y - 10.dp.toPx(),
                                Paint().apply {
                                    this.textSize = 20.sp.toPx()
                                    this.textAlign = Paint.Align.CENTER
                                    this.color = Color.BLACK
                                    this.style = Paint.Style.FILL
                                    this.isAntiAlias = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeightScale() {
    WeightScale(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp), onWeightSelected = {})
}