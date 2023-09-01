package com.example.batmanmoviesapp.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.batmanmoviesapp.data.model.MovieDetailResponse
import com.example.batmanmoviesapp.databinding.FragmentDetailBinding
import com.example.batmanmoviesapp.ui.base.UiState
import com.example.batmanmoviesapp.util.AppConstant
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var movieId: String
    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getString(AppConstant.MOVIE_ID).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMovieDetail(movieId)
        fetchMovieDetail()
    }

    private fun fetchMovieDetail() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailUiState.observe(viewLifecycleOwner) {
                    when (it) {
                        is UiState.Success -> {
                            initUi(it.data)
                        }
                        is UiState.Loading -> {
                            Log.d("getAllMoviesTAG", "setUpObserver: " + "loading")
                        }
                        is UiState.Error -> {
                            Log.d("getAllMoviesTAG", "setUpObserver: " + "Error")
                        }
                    }
                }
            }
        }
    }

    private fun initUi(movieDetailResponse: MovieDetailResponse) {
        Picasso.get().load(movieDetailResponse.poster).into(binding.image)
        binding.title.text = movieDetailResponse.title
        binding.year.text = movieDetailResponse.year
        binding.time.text = movieDetailResponse.runtime
        binding.imdb.text = movieDetailResponse.imdbRating
        binding.genre.text = movieDetailResponse.genre
        binding.plot.text = movieDetailResponse.plot
        binding.director.text = movieDetailResponse.director
        binding.actor.text = movieDetailResponse.actors
    }
}