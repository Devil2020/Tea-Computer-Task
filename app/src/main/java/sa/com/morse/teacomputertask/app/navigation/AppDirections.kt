package sa.com.morse.teacomputertask.app.navigation

import androidx.navigation.NavController

object  MoviesDirections {

    fun openHomeScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.HomeRoute.navigateAndPopCurrent()
        }
    }

    fun openMoviesScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.MoviesRoute.navigate()
        }
    }

    fun openMovieDetailsScreen(controller: NavController , id : Int) {
        with(controller) {
            MovieRoutes.DetailsRoute.navigate()
        }
    }


    fun openSeriesScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.SeriesRoute.navigate()
        }
    }

    fun openSearchScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.SearchRoute.navigate()
        }
    }

}