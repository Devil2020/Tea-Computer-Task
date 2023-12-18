package sa.com.morse.teacomputertask.local

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import sa.com.morse.teacomputertask.domain.models.MovieOrSeriesItem


class RoomAgentTest {

    private var apis: RoomApi? = null
    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    @Before
    fun setUp() {
        RoomAgent.turnTestingMode()
        apis = RoomAgent.createGateway(context).getContract()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddMovieOrSeriesToTable() {
        // Given
        val subscriber = TestObserver<Long>()
        val item = fakeItem
        //When
        apis?.addMovieOrSeriesItem(item)?.toObservable<Long>()
            ?.subscribe(subscriber)
        // Then
        subscriber.assertComplete()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testLoadAllCachedDataFromTable() {
        // Given
        val subscriber = TestObserver<List<MovieOrSeriesItem>>()
        val item = fakeItem
        //When
        apis?.addMovieOrSeriesItem(item)
        apis?.getMoviesOrSeries()
            ?.subscribe(subscriber)
        // Then
        subscriber.awaitCount(1).assertValue { it.size == 1 }
    }

}