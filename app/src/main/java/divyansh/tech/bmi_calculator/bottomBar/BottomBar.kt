package divyansh.tech.bmi_calculator.bottomBar

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import divyansh.tech.bmi_calculator.chips.BottomChipNavigation
import divyansh.tech.bmi_calculator.chips.ImageChip
import divyansh.tech.bmi_calculator.chips.SelectedImageChip
import divyansh.tech.bmi_calculator.chips.UnSelectedImageChip

@Composable
fun BottomNavBar(navController: NavController, items: List<BottomNavigationScreens>) {
    BottomChipNavigation(
        modifier = Modifier.fillMaxWidth().height(86.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Log.e("NAME -> ", currentRoute.toString())
        items.forEach {
            ImageChip(
                text = it.route,
                icon = it.icon,
                selected = it.route == currentRoute,
                onClick = {
                    navController.navigate(it.route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showBottomBar(){
    val navController = rememberNavController()
    val bottomNavItems = listOf(BottomNavigationScreens.Home, BottomNavigationScreens.Membership, BottomNavigationScreens.Settings)
    BottomNavBar(navController = navController, items = bottomNavItems)
}

