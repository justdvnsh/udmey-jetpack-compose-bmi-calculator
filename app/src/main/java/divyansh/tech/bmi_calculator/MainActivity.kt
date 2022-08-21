package divyansh.tech.bmi_calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import divyansh.tech.bmi_calculator.HeightPicker.HeightPicker
import divyansh.tech.bmi_calculator.WeightPicker.RoundSlider
import divyansh.tech.bmi_calculator.WeightPicker.WeightScale
import divyansh.tech.bmi_calculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var height by remember {
                mutableStateOf(0)
            }
            var weight by remember {
                mutableStateOf(0)
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)) {
                Column() {
                    Text(text = "BMI Calculator", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)

                    Spacer(modifier = Modifier.padding(vertical = 12.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        HeightPicker(modifier = Modifier
                            .fillMaxHeight()
                            .width(140.dp), onHeightPicked = {
                            height = it
                        })

                        Spacer(modifier = Modifier.padding(horizontal = 12.dp))

                        Column(modifier = Modifier.fillMaxHeight()) {
                            WeightScale(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp), onWeightSelected = {
                                    weight = it
                                })
                            Text(text = "HEIGHT :${height}", fontSize = 18.sp)
                            Text(text = "WEIGHT :${weight}", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}