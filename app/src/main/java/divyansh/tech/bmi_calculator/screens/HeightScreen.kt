package divyansh.tech.bmi_calculator.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import divyansh.tech.bmi_calculator.HeightPicker.HeightPicker
import divyansh.tech.bmi_calculator.R
import divyansh.tech.bmi_calculator.ui.theme.quickSand

@Composable
fun HeightScreen() {

    var height by remember {
        mutableStateOf(0)
    }

    BoxWithConstraints() {
        val maxHeight = maxHeight
        val maxWidth = maxWidth
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HeightPicker(modifier = Modifier
                .fillMaxHeight()
                .width(160.dp), onHeightPicked = {
                height = it
            })

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(maxWidth / 2),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Height $height",
                    fontFamily = quickSand,
                    fontWeight = FontWeight.Bold,
                    fontSize = 46.sp,
                    textAlign = TextAlign.Center
                )

                Image(
                    painter = painterResource(id = R.drawable.person_male),
                    contentDescription = "",
                    modifier = Modifier.height(maxHeight/2).fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showHeightScreen() {
    HeightScreen()
}