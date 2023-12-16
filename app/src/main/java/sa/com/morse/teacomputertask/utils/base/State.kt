package sa.com.morse.teacomputertask.utils.base

fun <Response> State<Response>.onLoading(`do`: () -> Unit) = apply {
    if (this is State.Loading) {
        `do`.invoke()
    }
}

fun <Response> State<Response>.onFail(`do`: (ExceptionType) -> Unit) = apply {
    if (this is State.Error) {
        `do`.invoke(this.exceptionType)
    }
}

fun <Response> State<Response>.onSuccess(`do`: (Response) -> Unit) = apply {
    if (this is State.Success) {
        `do`.invoke(this.data)
    }
}

sealed class State<out T> {

    data object Loading : State<Nothing>()

    data class Success<out T>(val data: T) : State<T>()

    data class Error(
        val exceptionType: ExceptionType,
    ) : State<Nothing>()

}