package sa.com.morse.teacomputertask.ui.preview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.koinViewModel
import sa.com.morse.teacomputertask.domain.usecases.LoadMostSearchedListUseCase
import sa.com.morse.teacomputertask.ui.screens.details.DetailScreen
import sa.com.morse.teacomputertask.ui.screens.getstart.GetStartedScreen
import sa.com.morse.teacomputertask.ui.screens.home.HomeScreen
import sa.com.morse.teacomputertask.ui.screens.home.HomeViewModel
import sa.com.morse.teacomputertask.ui.screens.movies.MoviesScreen
import sa.com.morse.teacomputertask.ui.screens.search.SearchScreen
import sa.com.morse.teacomputertask.ui.screens.series.SeriesScreen

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "GetStartedScreen",
    device = Devices.PHONE,
    group = "Task"
)
@Composable
fun PreviewGetStartedScreen() {
    GetStartedScreen {}
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "HomeScreen",
    device = Devices.PHONE,
    group = "Task"
)
@Composable
fun PreviewHomeScreen() {
        HomeScreen(
            vm = null,
            openMovies = {},
            openSeries = {},
            openSearch = {},
            openDetails = { _, _ -> })
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "MoviesScreen",
    device = Devices.PHONE,
    group = "Task"
)
@Composable
fun PreviewMoviesScreen() {
    MoviesScreen(vm = null, openDetails = { _, _ -> }, finish = {})
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "SeriesScreen",
    device = Devices.PHONE,
    group = "Task"
)
@Composable
fun PreviewSeriesScreen() {
    SeriesScreen(vm = null, openDetails = { _, _ -> }, finish = {})
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "SearchScreen",
    device = Devices.PHONE,
    group = "Task"
)
@Composable
fun PreviewSearchScreen() {
    SearchScreen(vm = null, openDetails = { _, _ -> }, finish = {})
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "DetailScreen",
    device = Devices.PHONE,
    group = "Task"
)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(vm = null,)
}