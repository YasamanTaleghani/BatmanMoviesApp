package com.example.batmanmoviesapp.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batmanmoviesapp.data.model.Movie
import com.example.batmanmoviesapp.databinding.FragmentHomeBinding
import com.example.batmanmoviesapp.ui.base.MovieAdapter
import com.example.batmanmoviesapp.ui.base.UiState
import com.example.batmanmoviesapp.util.AppConstant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MovieAdapter
    @Inject lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObserver()
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MovieAdapter()
        binding.recyclerView.adapter = adapter
        adapter.setOnClickListener(object :
            MovieAdapter.OnClickListener {
            override fun onClick(model: Movie) {
                setCurrentFragment(DetailFragment(), model)
            }
        })
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.observe(viewLifecycleOwner) {
                    when (it) {
                        is UiState.Success -> {
                            Log.d("getAllMoviesTAG", "setUpObserver: " + "Success")
                            adapter.setMovies(ArrayList(it.data))
                            if (viewModel.localItemSize == 0) {
                                viewModel.insertAllLocalMovies(it.data)
                            }
                        }
                        is UiState.Loading -> {
                            Log.d("getAllMoviesTAG", "setUpObserver: " + "loading")
                        }
                        is UiState.Error -> {
                            Log.d("getAllMoviesTAG", "setUpObserver: " + "Error")
                            viewModel.fetchAllLocalMovies()
                        }
                    }
                }
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment, movie: Movie) {
        val args = Bundle()
        args.putString(AppConstant.MOVIE_ID, movie.imdbID)
        fragment.arguments = args
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(com.example.batmanmoviesapp.R.id.container, fragment)
            commit()
        }
    }
}