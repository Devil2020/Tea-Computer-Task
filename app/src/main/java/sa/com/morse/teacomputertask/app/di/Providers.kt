package sa.com.morse.teacomputertask.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sa.com.morse.teacomputertask.data.repository.MoviesRepository
import sa.com.morse.teacomputertask.data.repository.SeriesRepository
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.domain.repositories.ISeriesRepository
import sa.com.morse.teacomputertask.domain.usecases.LoadASeriesOfDetailUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadMovieDetailUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadMoviesUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadSeriesUseCase
import sa.com.morse.teacomputertask.domain.usecases.SearchUseCase
import sa.com.morse.teacomputertask.remote.RetrofitAgent
import sa.com.morse.teacomputertask.ui.screens.details.DetailViewModel
import sa.com.morse.teacomputertask.ui.screens.home.HomeViewModel
import sa.com.morse.teacomputertask.ui.screens.movies.MoviesViewModel
import sa.com.morse.teacomputertask.ui.screens.search.SearchViewModel
import sa.com.morse.teacomputertask.ui.screens.series.SeriesViewModel

object Providers {
    val remoteModule = module {
        single {
            RetrofitAgent.createGateway()
        }
    }

    val localModule = module {
        single {
            RetrofitAgent.createGateway()
        }
    }

    val dataModule = module {
        single<IMoviesRepository> {
            MoviesRepository(get())
        }
        single<ISeriesRepository> {
            SeriesRepository(get())
        }
    }

    val domainModule = module {
        single {
            LoadASeriesOfDetailUseCase(get())
        }
        single {
            LoadMovieDetailUseCase(get())
        }
        single {
            LoadMoviesUseCase(get())
        }
        single {
            LoadSeriesUseCase(get())
        }
        single {
            SearchUseCase(get() , get())
        }
    }
    val viewModelModule = module {
        viewModel { HomeViewModel() }
        viewModel { MoviesViewModel(get()) }
        viewModel { SeriesViewModel(get()) }
        viewModel { DetailViewModel(get() , get() , get()) }
        viewModel { SearchViewModel(get()) }
    }
}