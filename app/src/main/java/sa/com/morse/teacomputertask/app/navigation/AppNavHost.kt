package sa.com.morse.teacomputertask.app.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sa.com.morse.teacomputertask.ui.screens.getstart.GetStartedScreen

@Composable
fun MovieNavigation() {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = MovieRoutes.GetStartedRoute.name
    ) {
        composable(MovieRoutes.GetStartedRoute.name){
            GetStartedScreen()
        }
    }
}