package com.example.batmanmoviesapp.ui.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.batmanmoviesapp.data.model.Movie
import com.example.batmanmoviesapp.databinding.MovieListItemBinding
import com.squareup.picasso.Picasso

class MovieAdapter(private var movieList: ArrayList<Movie> = arrayListOf()) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    lateinit var binding: MovieListItemBinding
    private var onClickListener: OnClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(newMovieList: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.title.text = item.title
            binding.year.text = item.year
            Picasso
                .get()
                .load(item.poster)
                .into(binding.image)
            binding.root.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(item )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MovieListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    interface OnClickListener {
        fun onClick(model: Movie)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
}