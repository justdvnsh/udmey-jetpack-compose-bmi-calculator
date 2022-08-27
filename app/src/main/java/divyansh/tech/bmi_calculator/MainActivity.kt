package divyansh.tech.bmi_calculator

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import divyansh.tech.bmi_calculator.AgePicker.NumberPicker
import divyansh.tech.bmi_calculator.HeightPicker.HeightPicker
import divyansh.tech.bmi_calculator.WeightPicker.RoundSlider
import divyansh.tech.bmi_calculator.WeightPicker.WeightScale
import divyansh.tech.bmi_calculator.bottomBar.BottomBar
import divyansh.tech.bmi_calculator.bottomBar.BottomNavigationScreens
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
            val navController = rememberNavController()
            val bottomNavItems = listOf(BottomNavigationScreens.HOME, BottomNavigationScreens.MEMBERSHIP, BottomNavigationScreens.SETTINGS)
            Scaffold(
                bottomBar = {
                    BottomBar(navController, bottomNavItems)
                }
            ) {
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
                            Column() {
                                Text(text = "Height", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)

                                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                                HeightPicker(modifier = Modifier
                                    .fillMaxHeight()
                                    .width(140.dp), onHeightPicked = {
                                    height = it
                                })
                            }

                            Spacer(modifier = Modifier.padding(horizontal = 12.dp))

                            Column(modifier = Modifier.fillMaxHeight()) {

                                Text(text = "Weight", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)

                                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                                WeightScale(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(150.dp), onWeightSelected = {
                                        weight = it
                                    })
//                            Text(text = "HEIGHT :${height}", fontSize = 18.sp)
//                            Text(text = "WEIGHT :${weight}", fontSize = 18.sp)

                                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                                Text(text = "Age", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)

                                Spacer(modifier = Modifier.padding(vertical = 10.dp))

                                NumberPicker(
                                    value = 21,
                                    onValueChange = {},
                                    range = 5..100,
                                    modifier = Modifier.fillMaxWidth(),
                                    dividersColor = Color.LightGray,
                                    textStyle = TextStyle(
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}