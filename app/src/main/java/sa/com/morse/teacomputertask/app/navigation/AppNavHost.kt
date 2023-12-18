package sa.com.morse.teacomputertask.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import sa.com.morse.teacomputertask.ui.screens.details.DetailScreen
import sa.com.morse.teacomputertask.ui.screens.getstart.GetStartedScreen
import sa.com.morse.teacomputertask.ui.screens.home.HomeScreen
import sa.com.morse.teacomputertask.ui.screens.movies.MoviesScreen
import sa.com.morse.teacomputertask.ui.screens.movies.MoviesViewModel
import sa.com.morse.teacomputertask.ui.screens.search.SearchScreen
import sa.com.morse.teacomputertask.ui.screens.series.SeriesScreen

@Composable
fun MovieNavigation() {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = MovieRoutes.GetStartedRoute.name
    ) {
        composable(MovieRoutes.GetStartedRoute.name) {
            GetStartedScreen {
                MoviesDirections.openHomeScreen(controller)
            }
        }
        composable(MovieRoutes.HomeRoute.name) {
            HomeScreen(vm = koinViewModel() , openMovies = { MoviesDirections.openMoviesScreen(controller) },
                openSeries = { MoviesDirections.openSeriesScreen(controller) },
                openSearch = { MoviesDirections.openSearchScreen(controller) },
                openDetails = { id, isMovie ->
                    MoviesDirections.openMovieDetailsScreen(controller, id, isMovie)
                })
        }
        composable(
            MovieRoutes.DetailsRoute.name + "/{id}/{isMovie}", arguments = arrayListOf(
                navArgument("id") {
                    type = NavType.IntType
                    nullable = false
                },
                navArgument("isMovie") {
                    type = NavType.BoolType
                    nullable = false
                },
            )
        ) {
            DetailScreen(vm = koinViewModel())
        }
        composable(MovieRoutes.SearchRoute.name) {
            SearchScreen(vm = koinViewModel(), openDetails = { id, isMovie ->
                MoviesDirections.openMovieDetailsScreen(controller, id, isMovie)
            }, finish = { MoviesDirections.closeSearchScreen(controller) })
        }
        composable(MovieRoutes.MoviesRoute.name) {
            MoviesScreen(vm = koinViewModel(), openDetails = { id, isMovie ->
                MoviesDirections.openMovieDetailsScreen(controller, id, isMovie)
            }, finish = { MoviesDirections.closeMoviesScreen(controller) })
        }
        composable(MovieRoutes.SeriesRoute.name) {
            SeriesScreen(vm = koinViewModel(), openDetails = { id, isMovie ->
                MoviesDirections.openMovieDetailsScreen(controller, id, isMovie)
            }, finish = { MoviesDirections.closeSeriesScreen(controller) })
        }
    }
}