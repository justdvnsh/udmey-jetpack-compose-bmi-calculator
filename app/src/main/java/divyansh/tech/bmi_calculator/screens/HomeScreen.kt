package divyansh.tech.bmi_calculator.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import divyansh.tech.bmi_calculator.NavigationGraph
import divyansh.tech.bmi_calculator.StepProgress.Step
import divyansh.tech.bmi_calculator.StepProgress.StepsProgressBar
import divyansh.tech.bmi_calculator.bottomBar.BottomNavigationScreens

@Composable
fun HomeScreen() {
    var currentStep by remember {
        mutableStateOf(1)
    }
    val navController = rememberNavController()
    Scaffold() {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)) {
            Column() {
                Spacer(modifier = Modifier.padding(vertical = 12.dp))

                StepsProgressBar(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Step(onClick = {
                        navController.navigate(BottomNavigationScreens.HeightScreen.route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
                    Step(onClick = {
                        navController.navigate(BottomNavigationScreens.WeightScreen.route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
                    Step(onClick = {})
                }

                Spacer(modifier = Modifier.padding(vertical = 12.dp))

                HomeScreeNavigation(navController = navController)
            }
        }
    }
}

@Composable
fun HomeScreeNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavigationScreens.HeightScreen.route) {
        composable(BottomNavigationScreens.HeightScreen.route) {
            HeightScreen()
        }
        composable(BottomNavigationScreens.WeightScreen.route) {
            WeightScreen()
        }
    }
}