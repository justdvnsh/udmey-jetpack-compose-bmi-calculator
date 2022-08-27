package divyansh.tech.bmi_calculator.bottomBar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import divyansh.tech.bmi_calculator.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourseId: Int,
    val icon: ImageVector
) {

    companion object {
        const val HOME = "Home"
        const val MEMBERSHIP = "Membership"
        const val SETTINGS = "Settings"
    }

    object Home: BottomNavigationScreens(HOME, R.string.home, Icons.Outlined.Home)
    object Membership: BottomNavigationScreens(MEMBERSHIP, R.string.home, Icons.Outlined.AddCircle)
    object Settings: BottomNavigationScreens(SETTINGS, R.string.home, Icons.Outlined.Settings)
    object HeightScreen: BottomNavigationScreens("Height", R.string.home, Icons.Outlined.Settings)
    object WeightScreen: BottomNavigationScreens("Weight", R.string.home, Icons.Outlined.Settings)
    object SpeedometerScreen: BottomNavigationScreens("Speedometer", R.string.home, Icons.Outlined.Settings)
}