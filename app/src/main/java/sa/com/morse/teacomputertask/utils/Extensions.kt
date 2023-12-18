package sa.com.morse.teacomputertask.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun  Boolean.onTrue(`do`: () -> Unit): Boolean {
    if (this) {
        `do`.invoke()
    }
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun  Boolean.onFalse(`do`: () -> Unit): Boolean {
    if (!this) {
        `do`.invoke()
    }
    return this
}