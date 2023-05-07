package com.binar.challenge5.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.binar.challenge5.R
import com.binar.challenge5.databinding.MovieListBinding
import com.binar.challenge5.model.Result
import com.bumptech.glide.Glide

class MovieAdapter(var listMovie: List<Result>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(var binding : MovieListBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = MovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
       holder.binding.titleMovie.text = listMovie[position].title
        holder.binding.releaseMovie.text = listMovie[position].releaseDate
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w780${listMovie[position].backdropPath}")
            .into(holder.binding.imgMovie)
        holder.binding.cardView.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("data_movie", listMovie[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment2, bundle)
        }
    }

    override fun getItemCount(): Int = listMovie.size
}