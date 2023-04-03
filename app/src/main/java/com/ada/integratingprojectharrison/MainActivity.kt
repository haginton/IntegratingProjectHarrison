package com.ada.integratingprojectharrison

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ada.integratingprojectharrison.databinding.ActivityMainBinding
import com.ada.integratingprojectharrison.network.MoviesService
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var moviesService: MoviesService

    //private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonAction: Button = findViewById(R.id.buttonAction)
        val movieCategory: TextView = findViewById(R.id.movieCategory)
        val movieNumber: TextView = findViewById(R.id.movieNumber)

        buttonAction.setOnClickListener {
            val valueMovieCategory: String = movieCategory.text.toString()
            val valueMovieNumber: String = movieNumber.text.toString()

            val castValueMovieNumber: Int = valueMovieNumber.toInt()

            requestMoviesData(valueMovieCategory, castValueMovieNumber)
        }



    }

    private fun requestMoviesData(movieCategory: String, movieNumber: Int) {

        GlobalScope.launch {

            //val moviesService = RetrofitGenerator.getInstance().create(MoviesService::class.java)
            val response = moviesService.searchMovies(movieCategory)
            if (response.isSuccessful) {
                val movies = response.body()
                val movieToDisplay = movies!!.Search[movieNumber]

                runOnUiThread {
                    binding.movieTitle.text = movieToDisplay.Title
                    binding.movieYear.text = movieToDisplay.Year
                    Glide.with(baseContext).load(movieToDisplay.Poster).into(binding.moviePoster)
                }


            } else {
                println(response.errorBody())
            }

            /*val moviesService = RetrofitGenerator.getInstance().create(MoviesService::class.java)
            val response = moviesService.searchMovies(movieCategory)
            if (response.isSuccessful) {
                val movies = response.body()
                val movieToDisplay = movies!!.Search[movieNumber]

                runOnUiThread {
                    binding.movieTitle.text = movieToDisplay.Title
                    binding.movieYear.text = movieToDisplay.Year
                    Glide.with(baseContext).load(movieToDisplay.Poster).into(binding.moviePoster)
                }


            } else {
                println(response.errorBody())
            }*/
        }

        /*val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

        val service: MoviesService = retrofit.create(MoviesService::class.java)*/
    }
}