package com.binar.challenge5.service

import com.binar.challenge5.model.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIMovieInterface {
    @GET("https://api.themoviedb.org/3/movie/popular?api_key=7d8d14c59d48644acd9d52f2409a2dab&language=en-US&page=1")
    fun getPopularMovie(): Call<MoviePopular<com.binar.challenge5.model.Result>>

}