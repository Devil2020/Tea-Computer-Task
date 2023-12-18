package sa.com.morse.teacomputertask.utils

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.SocketTimeoutException
import java.net.UnknownHostException


abstract class SingleUseCase<in IncomingParameterType, out OutComingResultType>() {

    operator fun invoke(args: IncomingParameterType? = null): Observable<State<@UnsafeVariance OutComingResultType>> =
        execute(args)
            .subscribeOn(Schedulers.io())
            .onErrorReturn {
                State.Error(
                    it.toExceptionType()
                )
            }.startWithItem(State.Loading)

    protected abstract fun execute(args: IncomingParameterType? = null): Observable<State<@UnsafeVariance OutComingResultType>>

}

class GenericBaseUseCase<in InputType, out ResultType>(private val exe: (args: InputType?) -> Observable<State<ResultType>>) :
    SingleUseCase<InputType, ResultType>() {

    override fun execute(args: InputType?): Observable<State<@UnsafeVariance ResultType>> {
        return exe.invoke(args)
    }

}

fun Throwable.toExceptionType(): ExceptionType {
    return when (this) {
        is SocketTimeoutException -> return ExceptionType.SocketTimeoutException
        is javax.net.ssl.SSLHandshakeException -> return ExceptionType.SSLHandshakeException
        is UnknownHostException -> return ExceptionType.UnknownHostException
        is java.net.ProtocolException -> return ExceptionType.ProtocolException
        is javax.net.ssl.SSLException -> return ExceptionType.SSLException
        is java.net.SocketException -> return ExceptionType.SocketException
        is java.io.EOFException -> return ExceptionType.EOFException
        is java.util.concurrent.CancellationException -> return ExceptionType.UserCancellationException
        else -> return ExceptionType.GenericException(this.localizedMessage ?: "Generic Exception")
    }
}

sealed class ExceptionType() {
    object HttpException404 : ExceptionType()
    object HttpException422 : ExceptionType()
    object HttpException403 : ExceptionType()
    object HttpException504 : ExceptionType()
    object HttpException401 : ExceptionType()
    object HttpException400 : ExceptionType()
    object HttpExceptionGeneric : ExceptionType()
    object SocketTimeoutException : ExceptionType()
    object SSLHandshakeException : ExceptionType()
    object UnknownHostException : ExceptionType()
    object ProtocolException : ExceptionType()
    object SSLException : ExceptionType()
    object SocketException : ExceptionType()
    object EOFException : ExceptionType()
    object UserCancellationException : ExceptionType()
    data class GenericException(val message: String) : ExceptionType()
}

