package com.huypham998.movieapp.adapter

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.huypham998.movieapp.R
import com.huypham998.movieapp.extensions.loadImage
import com.huypham998.movieapp.model.MoviePopular
import kotlinx.android.synthetic.main.item_recyclerview_movie_popular.view.*

class RecyclerViewMoviePopularAdapter(var data:MutableList<MoviePopular.ResultMoviePopular>,val itemClick: (data: MoviePopular.ResultMoviePopular)->Unit) :
    RecyclerView.Adapter<RecyclerViewMoviePopularAdapter.MoviePopularViewHolder>(){

    inner class MoviePopularViewHolder(view: View) : RecyclerView.ViewHolder(view){

        init {
            view.setOnClickListener {
                itemClick(data[adapterPosition])
            }
        }
        fun bindData(movie: MoviePopular.ResultMoviePopular){

            if(movie.posterPath==null){
                itemView.imgMoviePopular.setImageResource(R.drawable.slide1)
            }else{
                Log.i("huykute",movie.posterPath)
                itemView.imgMoviePopular.apply {
                    loadImage("https://image.tmdb.org/t/p/original${movie.posterPath}")
                }
            }

            itemView.txtTitleMoviePopular.text = movie.title
            itemView.ratingItemMoviePopular.rating=(movie.voteAverage/2).toFloat()
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MoviePopularViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MoviePopularViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_movie_popular,parent,false)

        return MoviePopularViewHolder(layoutInflater)
    }




}