package com.huypham998.movieapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.huypham998.movieapp.R
import com.huypham998.movieapp.adapter.RecyclerViewMoviePopularAdapter
import com.huypham998.movieapp.adapter.SlidePageAdapter
import com.huypham998.movieapp.model.MoviePopular
import com.huypham998.movieapp.model.Slide
import com.huypham998.movieapp.network.ApiProvider
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    private var isItemClicked: Boolean = false
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val api_provider: ApiProvider by lazy {
        ApiProvider.create()
    }
    private var listSlide = mutableListOf<Slide>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show()
        setContentView(R.layout.activity_home)

        addDataSlide()

        //Toast.makeText(this,listSlide.size.toString(),Toast.LENGTH_LONG).show()
        addSlideAdapter()


        handleMoviePopular()

        handleMovieTopRate()

        handleMovieTopUpcoming()
    }



    fun handleMoviePopular(){
        var linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        launch {

            var result = withContext(Dispatchers.IO) {
                api_provider.listMoviePopular()
            }
            withContext(Dispatchers.Main) {
                var data = result.results
                var recyclerViewMoviePopularAdapter =
                    RecyclerViewMoviePopularAdapter(data as MutableList<MoviePopular.ResultMoviePopular>) {
                        //Toast.makeText(applicationContext,it.title,Toast.LENGTH_LONG).show()
                        if (!isItemClicked) {
                            isItemClicked = true
                            var intent = Intent(applicationContext, MovieDetailActivity::class.java)
                            intent.apply {
                                putExtra("imgUrlPoster", it.posterPath)
                            }
                            startActivity(intent)
                        }
                    }
                recyclerMoviePopular.apply {
                    adapter = recyclerViewMoviePopularAdapter
                    layoutManager = linearLayoutManager
                    setItemViewCacheSize(5)
                }

            }

        }
    }

    fun handleMovieTopRate(){
        var linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        launch {
           var movieTopRate= withContext(Dispatchers.IO){
                api_provider.listMovieTopRate()
            }

            withContext(Dispatchers.Main){
                var data = movieTopRate.results
                var recyclerViewMoviePopularAdapter=RecyclerViewMoviePopularAdapter(data as MutableList<MoviePopular.ResultMoviePopular>){
                    var intent = Intent(applicationContext, MovieDetailActivity::class.java)
                    intent.apply {
                        putExtra("imgUrlPoster", it.posterPath)
                    }
                    startActivity(intent)
                }
                recyclerTopRate.apply {
                    adapter = recyclerViewMoviePopularAdapter
                    layoutManager = linearLayoutManager
                    setItemViewCacheSize(5)
                }
            }

        }
    }
    fun handleMovieTopUpcoming(){
        var linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        launch {
            var movieTopUpcoming = withContext(Dispatchers.IO){
                api_provider.listMovieUpcoming()
            }
            var data  = movieTopUpcoming.results

            var recyclerViewMoviePopularAdapter= RecyclerViewMoviePopularAdapter(data as MutableList<MoviePopular.ResultMoviePopular>){
                var intent = Intent(applicationContext, MovieDetailActivity::class.java)
                intent.apply {
                    putExtra("imgUrlPoster", it.posterPath)
                }
                startActivity(intent)
            }
            recyclerTopUpcoming.apply {
                adapter = recyclerViewMoviePopularAdapter
                layoutManager = linearLayoutManager
                setItemViewCacheSize(5)
            }
        }
    }
    fun addDataSlide() {
        listSlide.apply {
            add(Slide(R.drawable.slide1, "Phim1"))
            add(Slide(R.drawable.slide2, "Phim2"))
            add(Slide(R.drawable.slide1, "Phim3"))
        }
    }

    fun addSlideAdapter() {
        var slidePageAdapter = SlidePageAdapter(this, listSlide)

        viewSlidePage.adapter = slidePageAdapter
        indicator.setupWithViewPager(viewSlidePage, true)
    }

    override fun onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show()
        job.cancel()
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show()

    }


    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "OnResume", Toast.LENGTH_LONG).show()

        isItemClicked = false
    }

    override fun onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show()
        super.onPause()
    }

    override fun onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show()
        super.onStop()
    }
}
