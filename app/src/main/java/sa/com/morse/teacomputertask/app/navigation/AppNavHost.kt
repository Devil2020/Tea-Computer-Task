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
        startDestination = MovieRoutes.DetailsRoute.name
    ) {
        composable(MovieRoutes.GetStartedRoute.name){
            GetStartedScreen()
        }
        composable(MovieRoutes.HomeRoute.name){
            HomeScreen()
        }
        composable(MovieRoutes.DetailsRoute.name){
            DetailScreen()
        }
        composable(MovieRoutes.SearchRoute.name){
            SearchScreen()
        }
        composable(MovieRoutes.MoviesRoute.name){
            MoviesScreen()
        }
        composable(MovieRoutes.SeriesRoute.name){
            SeriesScreen()
        }
    }
}