package sa.com.morse.teacomputertask.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <Response> State<Response>.onLoading(`do`: () -> Unit): State<Response> {
    contract {
        callsInPlace(`do`, InvocationKind.EXACTLY_ONCE)
    }
    if (this is State.Loading) {
        `do`.invoke()
    }
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <Response> State<Response>.onFail(`do`: (ExceptionType) -> Unit): State<Response> {
    contract {
        callsInPlace(`do`, InvocationKind.EXACTLY_ONCE)
    }
    if (this is State.Error) {
        `do`.invoke(this.exceptionType)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <Response> State<Response>.onSuccess(`do`: (Response) -> Unit): State<Response> {
    contract {
        callsInPlace(`do`, InvocationKind.EXACTLY_ONCE)
    }
    if (this is State.Success) {
        `do`.invoke(this.data)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <Response> ArrayList<Response>.onNotEmpty(`do`: (ArrayList<Response>) -> Unit): ArrayList<Response> {
    contract {
        callsInPlace(`do`, InvocationKind.EXACTLY_ONCE)
    }
    if (this.isNotEmpty()) {
        `do`.invoke(this)
    }
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <Response> ArrayList<Response>.onEmpty(`do`: (ArrayList<Response>) -> Unit): ArrayList<Response> {
    contract {
        callsInPlace(`do`, InvocationKind.EXACTLY_ONCE)
    }
    if (this.isEmpty()) {
        `do`.invoke(this)
    }
    return this
}

sealed class State<out T> {

    data object Loading : State<Nothing>()

    data class Success<out T>(val data: T) : State<T>()

    data class Error(
        val exceptionType: ExceptionType,
    ) : State<Nothing>()

}