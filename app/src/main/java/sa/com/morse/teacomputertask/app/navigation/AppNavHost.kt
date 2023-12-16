package sa.com.morse.teacomputertask.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sa.com.morse.teacomputertask.ui.screens.details.DetailScreen
import sa.com.morse.teacomputertask.ui.screens.getstart.GetStartedScreen
import sa.com.morse.teacomputertask.ui.screens.home.HomeScreen
import sa.com.morse.teacomputertask.ui.screens.movies.MoviesScreen
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
            HomeScreen(openMovies = { MoviesDirections.openMoviesScreen(controller) },
                openSeries = { MoviesDirections.openSeriesScreen(controller) },
                openSearch = { MoviesDirections.openSearchScreen(controller) },
                openDetails = { MoviesDirections.openMovieDetailsScreen(controller , it) })
        }
        composable(MovieRoutes.DetailsRoute.name) {
            DetailScreen()
        }
        composable(MovieRoutes.SearchRoute.name) {
            SearchScreen{
                MoviesDirections.openMovieDetailsScreen(controller , it)
            }
        }
        composable(MovieRoutes.MoviesRoute.name) {
            MoviesScreen{
                MoviesDirections.openMovieDetailsScreen(controller , it)
            }
        }
        composable(MovieRoutes.SeriesRoute.name) {
            SeriesScreen{
                MoviesDirections.openMovieDetailsScreen(controller , it)
            }
        }
    }
}