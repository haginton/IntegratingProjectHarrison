package com.ada.integratingprojectharrison.service

import com.ada.integratingprojectharrison.data.SearchResultDto
import com.ada.integratingprojectharrison.network.MoviesService
import com.bumptech.glide.Glide

class MovieFinderServiceRetrofit/*: MovieFinderService */{
    /*override fun findMoviesByTitle(tittle: String,  movieNumber: Int): SearchResultDto {
        /*val moviesService: MoviesService
        val response = moviesService.searchMovies(tittle)
        if (response.isSuccessful) {
            val movies = response.body()
            val movieToDisplay = movies!!.Search[movieNumber]

            /*runOnUiThread {
                binding.movieTitle.text = movieToDisplay.Title
                binding.movieYear.text = movieToDisplay.Year
                Glide.with(baseContext).load(movieToDisplay.Poster).into(binding.moviePoster)
            }*/


        } else {
            println(response.errorBody())
        }*/

    }*/
}