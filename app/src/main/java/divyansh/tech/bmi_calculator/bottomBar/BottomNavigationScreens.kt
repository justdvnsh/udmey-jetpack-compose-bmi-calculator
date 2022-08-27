package divyansh.tech.bmi_calculator.bottomBar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import divyansh.tech.bmi_calculator.R

sealed class BottomNavigationScreens(
    val route: String,
    @StringRes val resourseId: Int, val icon: ImageVector
) {

    companion object {
        const val HOME = "Home"
        const val MEMBERSHIP = "Membership"
        const val SETTINGS = "Settings"
    }

    object Home: BottomNavigationScreens(HOME, R.string.home, Icons.Outlined.Home)
    object Membership: BottomNavigationScreens(MEMBERSHIP, R.string.home, Icons.Outlined.Home)
    object Settings: BottomNavigationScreens(SETTINGS, R.string.home, Icons.Outlined.Home)
}