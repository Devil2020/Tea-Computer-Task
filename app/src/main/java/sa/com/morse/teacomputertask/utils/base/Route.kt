package sa.com.morse.teacomputertask.utils.base

import androidx.navigation.NavController


open class Route (val name : String) {

    context(NavController)
    fun navigate () = navigate(name)

    context(NavController)
    fun navigateAndPopCurrent () {
        pop()
        navigate()
    }

    context(NavController)
    private fun pop() = popBackStack()

}