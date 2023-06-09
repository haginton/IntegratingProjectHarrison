package com.ada.integratingprojectharrison.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ada.integratingprojectharrison.R
import com.ada.integratingprojectharrison.data.LoginDto
import com.ada.integratingprojectharrison.data.TokenDto
import com.ada.integratingprojectharrison.databinding.ActivityMainBinding
import com.ada.integratingprojectharrison.network.AuthService
import com.ada.integratingprojectharrison.network.MoviesService
import com.ada.integratingprojectharrison.network.ProductsService
import com.ada.integratingprojectharrison.storage.LocalStorage
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var moviesService: MoviesService

    @Inject
    lateinit var productsService: ProductsService
    //@Inject
    //lateinit var authService: AuthService

    //@Inject
    //lateinit var localStorage: LocalStorage;

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

        //requestAuthService()
        //Log.d("AndroidKotlinAda", "token from storage saved: ${localStorage.getToken()}")

    }

    /*private fun requestAuthService() {
        GlobalScope.launch {
            val loginDto = LoginDto(
                "ada6@mail.com",
                "1523asd*"
            )
            val response: Response<TokenDto> = authService.login(loginDto)

            if (response.isSuccessful){
                val token = response.body()
                Log.d("AndroidKotlinAda", "token: ${token!!.token}")
                localStorage.saveToken(token.token)
                Log.d("AndroidKotlinAda", "token from storage: ${localStorage.getToken()}")
            }
        }
    }*/

    private fun requestMoviesData(movieCategory: String, movieNumber: Int) {

        GlobalScope.launch {
            val response2 = productsService.getProduct("63db215f98077f6f4162ad1a")
            if (response2.isSuccessful){
                Log.d("AndroidKotlinAda", "response product: ${response2.body()}")
            }
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