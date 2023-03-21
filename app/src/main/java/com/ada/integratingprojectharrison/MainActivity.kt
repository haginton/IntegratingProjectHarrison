package com.ada.integratingprojectharrison

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.ada.integratingprojectharrison.data.RetrofitGenerator
import com.ada.integratingprojectharrison.data.SearchResultDto
import com.ada.integratingprojectharrison.databinding.ActivityMainBinding
import com.ada.integratingprojectharrison.network.MoviesService
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit



class MainActivity : AppCompatActivity() {

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestMoviesData()

    }

    private fun requestMoviesData() {

        GlobalScope.launch {
            val moviesService = RetrofitGenerator.getInstance().create(MoviesService::class.java)
            val response = moviesService.searchMovies("batman")
            if (response.isSuccessful) {
                val movies = response.body()
                val movieToDisplay = movies!!.Search[0]

                runOnUiThread {
                    binding.movieTitle.text = movieToDisplay.Title
                    binding.movieYear.text = movieToDisplay.Year
                    Glide.with(baseContext).load(movieToDisplay.Poster).into(binding.moviePoster)
                }


            } else {
                println(response.errorBody())
            }
        }

        /*val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

        val service: MoviesService = retrofit.create(MoviesService::class.java)*/
    }
}