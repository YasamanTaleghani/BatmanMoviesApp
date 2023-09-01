package com.example.batmanmoviesapp.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.batmanmoviesapp.data.local.MovieEntity
import com.example.batmanmoviesapp.data.model.Movie
import com.example.batmanmoviesapp.data.model.MovieDetailResponse
import com.example.batmanmoviesapp.data.repository.MovieLocalRepository
import com.example.batmanmoviesapp.data.repository.MovieRemoteRepository
import com.example.batmanmoviesapp.ui.base.MovieEntityAdapter
import com.example.batmanmoviesapp.ui.base.UiState
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val movieRemoteRepository: MovieRemoteRepository,
    private val movieLocalRepository: MovieLocalRepository) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<Movie>>>(UiState.Loading)
    val uiState: LiveData<UiState<List<Movie>>> = _uiState

    private val _detailUiState = MutableLiveData<UiState<MovieDetailResponse>>(UiState.Loading)
    val detailUiState: LiveData<UiState<MovieDetailResponse>> = _detailUiState

    var localItemSize: Int = 0

    init {
        fetchAllMoviesRemotely()
        getLocalMovieListSize()
    }

    private fun fetchAllMoviesRemotely() {
        viewModelScope.launch {
            _uiState.postValue(UiState.Loading)
            try {
                val movieList = movieRemoteRepository.getAllMovies("batman")
                _uiState.postValue(UiState.Success(movieList))
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    fun fetchMovieDetail(i: String) {
        viewModelScope.launch {
            _detailUiState.postValue(UiState.Loading)
            try {
                val movieDetail = movieRemoteRepository.getMovieDetail(i)
                _detailUiState.postValue(UiState.Success(movieDetail))
            } catch (e: Exception) {
                _detailUiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    fun fetchAllLocalMovies() {
        viewModelScope.launch {
            _uiState.postValue(UiState.Loading)
            try {
                val localMovieList = movieLocalRepository.getAllMovies()
                var movieList: ArrayList<Movie> = arrayListOf()
                for (item in localMovieList) {
                    movieList.add(MovieEntityAdapter.createMovie(item))
                }
                _uiState.postValue(UiState.Success(movieList))
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    fun insertAllLocalMovies(movieList: List<Movie>) {
        viewModelScope.launch {
            try {
                var movieEntityList: ArrayList<MovieEntity> = arrayListOf()
                for (item in movieList) {
                    movieEntityList.add(MovieEntityAdapter.createMovieEntity(item))
                }
                movieLocalRepository.insertAllMovies(movieEntityList)
            } catch (e: Exception) {
                _uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    private fun getLocalMovieListSize() {
        viewModelScope.launch {
            try {
                localItemSize = movieLocalRepository.movieListSize()
                Log.d("TAG", "getLocalMovieListSize: $localItemSize")
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}