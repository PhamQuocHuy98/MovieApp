package com.huypham998.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.huypham998.movieapp.R
import com.huypham998.movieapp.extensions.loadImage
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

       var img = intent.getStringExtra("imgUrlPoster")


        imageViewBackground.loadImage("https://image.tmdb.org/t/p/original$img")
    }
}
