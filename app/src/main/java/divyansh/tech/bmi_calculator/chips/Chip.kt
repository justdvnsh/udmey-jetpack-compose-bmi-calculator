package divyansh.tech.bmi_calculator.chips

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import divyansh.tech.bmi_calculator.ui.theme.quickSand

private val BottomNavigationAnimationSpec = TweenSpec<Float>(
    durationMillis = 1000,
    easing = LinearEasing
)

@Composable
fun BottomChipNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 36.dp,
            topEnd = 36.dp
        )
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content,
        )
    }
}

@Composable
private fun BottomNavigationTransition(
    activeColor: Color,
    inactiveColor: Color,
    selected: Boolean,
    content: @Composable (animationProgress: Float) -> Unit
) {
    val animationProgress by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = BottomNavigationAnimationSpec
    )

    val color = lerp(inactiveColor, activeColor, animationProgress)

    CompositionLocalProvider(
        LocalContentColor provides color.copy(alpha = 1f),
        LocalContentAlpha provides color.alpha,
    ) {
        content(animationProgress)
    }
}

@Composable
fun RowScope.ImageChip(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    selectedChipBackgroundColor: Color = Color.Transparent,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium)
) {

    val ripple = rememberRipple(bounded = false, color = Color.White)
    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .weight(1f),
        contentAlignment = Alignment.BottomCenter,
    ) {
        BottomNavigationTransition(
            selectedContentColor,
            unselectedContentColor,
            selected
        ) { progress ->

            BottomNavigationItemBaselineLayout(
                chip = {
                    when {
                        selected -> SelectedImageChip(text = text, icon = icon, selectedChipBackgroundColor)
                        else -> UnSelectedImageChip(icon = icon)
                    }
                }
            )
        }
    }
}

@Composable
private fun BottomNavigationItemBaselineLayout(
    chip: @Composable () -> Unit
) {
    Layout(
        {
            Box(Modifier.layoutId("chip")) { chip() }
        }
    ) { measurables, constraints ->
        val iconPlaceable = measurables.first { it.layoutId == "chip" }.measure(constraints)

        // If there is no label, just place the icon.
        placeIcon(iconPlaceable, constraints)
    }
}

/**
 * Places the provided [iconPlaceable] in the vertical center of the provided [constraints]
 */
private fun MeasureScope.placeIcon(
    iconPlaceable: Placeable,
    constraints: Constraints
): MeasureResult {
    val height = constraints.maxHeight
    val iconY = (height - iconPlaceable.height) / 2
    return layout(iconPlaceable.width, height) {
        iconPlaceable.placeRelative(0, iconY)
    }
}

@Composable
fun UnSelectedImageChip(
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.Transparent,
        contentColor = Color.White
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun SelectedImageChip(
    text: String,
    icon: ImageVector,
    selectedChipBackgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        color = selectedChipBackgroundColor,
        contentColor = Color.White,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.White
        ),
        modifier = modifier
    ) {
        // Inside a Row pack the Image and text together to
        // show inside the chip
        Row(modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = "",
                modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                text = text,
                fontFamily = quickSand,
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun showChip() {
    Surface(
        modifier = Modifier.background(MaterialTheme.colors.primary)
    ) {
//        ImageChip(text = "Home", icon = Icons.Filled.Home, selected = false,
//            modifier = Modifier.padding(12.dp))
    }
}