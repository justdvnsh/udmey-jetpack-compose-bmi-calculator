package divyansh.tech.bmi_calculator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import divyansh.tech.bmi_calculator.StepProgress.Step
import divyansh.tech.bmi_calculator.StepProgress.StepsProgressBar
import divyansh.tech.bmi_calculator.bottomBar.BottomNavigationScreens
import divyansh.tech.bmi_calculator.screens.Home.HomeViewModel

fun NavigateScreen(
    navController: NavHostController,
    screen: String = BottomNavigationScreens.HeightScreen.route
) {
    navController.navigate(screen) {

        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = HomeViewModel()
) {
    var currentStep by remember {
        mutableStateOf(1)
    }
    var screen by remember {
        mutableStateOf(BottomNavigationScreens.HeightScreen.route)
    }
    val navController = rememberNavController()
    Scaffold(
        floatingActionButton = {
            if (currentStep < 3) {
                FloatingActionButton(
                    onClick = {
                    if (currentStep < 3) {
                        currentStep++
                    }
                    screen = when(currentStep) {
                        1 -> BottomNavigationScreens.HeightScreen.route
                        2 -> BottomNavigationScreens.WeightScreen.route
                        3 -> BottomNavigationScreens.SpeedometerScreen.route
                        else -> BottomNavigationScreens.Settings.route
                    }
                    NavigateScreen(navController, screen)
                },
                backgroundColor = MaterialTheme.colors.primary) {
                    Icon(imageVector = Icons.Outlined.Done, contentDescription = "")
                }
            }
        },
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)) {
            Column() {
                Spacer(modifier = Modifier.padding(vertical = 12.dp))

                StepsProgressBar(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Step(selected = currentStep >= 1, onClick = {
                        currentStep = 1
                        screen = BottomNavigationScreens.HeightScreen.route
                        NavigateScreen(navController, screen)
                    })
                    Step(selected = currentStep >= 2, onClick = {
                        currentStep = 2
                        screen = BottomNavigationScreens.WeightScreen.route
                        NavigateScreen(navController, screen)
                    })
                    Step(selected = currentStep >= 3, onClick = {
                        currentStep = 3
                        screen = BottomNavigationScreens.SpeedometerScreen.route
                        NavigateScreen(navController, screen)
                    })
                }

                Spacer(modifier = Modifier.padding(vertical = 12.dp))

                HomeScreeNavigation(navController = navController, homeViewModel)
            }
        }
    }
}

@Composable
fun HomeScreeNavigation(navController: NavHostController, homeViewModel: HomeViewModel) {
    NavHost(navController = navController, startDestination = BottomNavigationScreens.HeightScreen.route) {
        composable(BottomNavigationScreens.HeightScreen.route) {
            HeightScreen(homeViewModel)
        }
        composable(BottomNavigationScreens.WeightScreen.route,) {
            WeightScreen(homeViewModel)
        }
        composable(BottomNavigationScreens.SpeedometerScreen.route) {
            BMIScreen(homeViewModel)
        }
    }
}