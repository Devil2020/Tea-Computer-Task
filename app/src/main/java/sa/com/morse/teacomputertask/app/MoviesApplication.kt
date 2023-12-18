package sa.com.morse.teacomputertask.app

import android.app.Application
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import sa.com.morse.teacomputertask.app.di.Providers
import java.util.logging.Handler


class MoviesApplication ()  :Application(){

    override fun onCreate() {
        super.onCreate()
        startInjectKoin()
        uncaughtExceptionHandler()
    }

    private fun uncaughtExceptionHandler(){
        RxJavaPlugins.setErrorHandler{

        }
        Thread.setDefaultUncaughtExceptionHandler { t, e ->

        }
    }
    private fun startInjectKoin (){
        startKoin {
            androidLogger()
            androidContext(this@MoviesApplication)
            modules(
                Providers.localModule ,
                Providers.remoteModule,
                Providers.dataModule,
                Providers.domainModule,
                Providers.viewModelModule,
            )
        }
    }

}
