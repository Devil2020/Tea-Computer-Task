package sa.com.morse.teacomputertask.utils

import androidx.navigation.NavController


open class Route(val name: String) {

    context(NavController)
    fun navigate() = navigate(name)

    context(NavController)
    fun navigateWithArguments(vararg args: String) = navigate(
        "$name${
            args.reduce { before, current ->
                "/$before/$current"
            }
        }"
    )

    context(NavController)
    fun navigateAndPopCurrent() {
        pop()
        navigate()
    }

    context(NavController)
    fun pop() = popBackStack()

}