package sa.com.morse.teacomputertask.app.navigation

import androidx.navigation.NavController

object MoviesDirections {

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

    fun closeMoviesScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.MoviesRoute.pop()
        }
    }

    fun closeSearchScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.SearchRoute.pop()
        }
    }

    fun openMovieDetailsScreen(controller: NavController, id: Int , isMovie : Boolean) {
        with(controller) {
            MovieRoutes.DetailsRoute.navigateWithArguments(id.toString() , isMovie.toString())
        }
    }


    fun openSeriesScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.SeriesRoute.navigate()
        }
    }

    fun closeSeriesScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.SeriesRoute.pop()
        }
    }

    fun openSearchScreen(controller: NavController) {
        with(controller) {
            MovieRoutes.SearchRoute.navigate()
        }
    }

}