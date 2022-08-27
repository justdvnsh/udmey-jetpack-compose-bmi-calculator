package divyansh.tech.bmi_calculator.screens.Home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var height: Int by mutableStateOf(0)
    var weight: Int by mutableStateOf(0)
}