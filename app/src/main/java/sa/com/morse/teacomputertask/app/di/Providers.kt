package sa.com.morse.teacomputertask.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import sa.com.morse.teacomputertask.data.repository.CommonRepository
import sa.com.morse.teacomputertask.data.repository.MoviesRepository
import sa.com.morse.teacomputertask.data.repository.SeriesRepository
import sa.com.morse.teacomputertask.domain.repositories.ICommonRepository
import sa.com.morse.teacomputertask.domain.repositories.IMoviesRepository
import sa.com.morse.teacomputertask.domain.repositories.ISeriesRepository
import sa.com.morse.teacomputertask.domain.usecases.LoadASeriesOfDetailUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadMostSearchedDetailUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadMostSearchedListUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadMovieDetailUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadMoviesUseCase
import sa.com.morse.teacomputertask.domain.usecases.LoadSeriesUseCase
import sa.com.morse.teacomputertask.domain.usecases.SaveDetailUseCase
import sa.com.morse.teacomputertask.domain.usecases.SaveMovieOrSeriesUseCase
import sa.com.morse.teacomputertask.domain.usecases.SearchUseCase
import sa.com.morse.teacomputertask.local.RoomAgent
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
            RoomAgent.createGateway(get()).getContract()
        }
    }

    val dataModule = module {
        single<IMoviesRepository> {
            MoviesRepository(get() , get())
        }
        single<ISeriesRepository> {
            SeriesRepository(get(), get())
        }
        single<ICommonRepository> {
            CommonRepository(get())
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
        single {
            SaveMovieOrSeriesUseCase(get())
        }
        single {
            SaveDetailUseCase(get())
        }
        single {
            LoadMostSearchedListUseCase(get())
        }
        single {
            LoadMostSearchedDetailUseCase(get())
        }
    }
    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { MoviesViewModel(get()) }
        viewModel { SeriesViewModel(get()) }
        viewModel { DetailViewModel(get() , get() , get()) }
        viewModel { SearchViewModel(get() , get()) }
    }
}