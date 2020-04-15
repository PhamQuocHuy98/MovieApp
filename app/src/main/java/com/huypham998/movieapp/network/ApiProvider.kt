package com.huypham998.movieapp.network

import com.huypham998.movieapp.model.MoviePopular
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
private const val API_KEY = "42519f135f15628eb628270a2ca1448f"
private const val LANGUAGE = "vi-VN"
interface  ApiProvider{
    @GET("movie/popular?api_key=42519f135f15628eb628270a2ca1448f&language=vi-VN")
    suspend fun listMoviePopular(): MoviePopular

    @GET("movie/top_rated?api_key=42519f135f15628eb628270a2ca1448f&language=vi-VN&page=1")
    suspend fun listMovieTopRate(): MoviePopular

    @GET("movie/upcoming?api_key=42519f135f15628eb628270a2ca1448f&language=vi-VN&page=1")
    suspend fun listMovieUpcoming(): MoviePopular

    companion object{
        fun create(): ApiProvider{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiProvider::class.java)
        }
    }
}