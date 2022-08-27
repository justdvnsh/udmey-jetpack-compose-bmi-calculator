package divyansh.tech.bmi_calculator

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import divyansh.tech.bmi_calculator.AgePicker.NumberPicker
import divyansh.tech.bmi_calculator.HeightPicker.HeightPicker
import divyansh.tech.bmi_calculator.WeightPicker.RoundSlider
import divyansh.tech.bmi_calculator.WeightPicker.WeightScale
import divyansh.tech.bmi_calculator.bottomBar.BottomNavBar
import divyansh.tech.bmi_calculator.bottomBar.BottomNavigationScreens
import divyansh.tech.bmi_calculator.screens.HomeScreen
import divyansh.tech.bmi_calculator.screens.MembershipScreen
import divyansh.tech.bmi_calculator.screens.SettingsScreen
import divyansh.tech.bmi_calculator.ui.theme.BMICalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val bottomNavItems = listOf(BottomNavigationScreens.Home, BottomNavigationScreens.Membership, BottomNavigationScreens.Settings)
            Scaffold(
                bottomBar = {
                    BottomNavBar(navController, bottomNavItems)
                }
            ) {
                NavigationGraph(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Glassmorpshism() {
    val scrollState = rememberScrollState()
    Box(
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize()
                .blur(7.dp),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xDD000000)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxHeight()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                fullCard(icon = Icons.Rounded.Home, 65, "San Francisco, California")
                fullCard(icon = Icons.Rounded.Home, 54, "Seattle, Washington")
                fullCard(icon = Icons.Rounded.Home, 2, "Detroit, Michigan")

            }

        }
    }
}


@Composable
fun fullCard(icon: ImageVector, temp: Int, place: String) {
    var tint = if (icon == Icons.Rounded.Home) {
        Color(0xFFE9E38C)
    } else if (icon == Icons.Rounded.Home) {
        Color(0xFF5CA9E6)
    } else {
        Color(0xFFFFFFFF)
    }
    Card(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .width(300.dp)
            .height(325.dp),
        backgroundColor = Color(0xFFFFFF),
        border = BorderStroke(1.dp, color = Color(0x25FFFFFF)),
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(1f)
                .blur(
                    radius = 28.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                )
                .background(
                    Brush.radialGradient(
                        listOf(
                            Color(0x12FFFFFF),
                            Color(0xDFFFFFF),
                            Color(0x9FFFFFFF)

                        ),
                        radius = 2200f,
                        center = Offset.Infinite
                    )
                )


        ) {

        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Sunny",
                modifier = Modifier.size(88.dp),
                tint = tint
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "$temp°",
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            Text(text = place)
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route) {
        composable(BottomNavigationScreens.Home.route) {
            HomeScreen()
        }
        composable(BottomNavigationScreens.Membership.route) {
            MembershipScreen()
        }
        composable(BottomNavigationScreens.Settings.route) {
            SettingsScreen()
        }
    }
}