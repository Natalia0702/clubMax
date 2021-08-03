package com.example.clubmax


import com.example.clubmax.ApiConsts.DEFAULT_QUERY
import com.example.clubmax.ApiConsts.PATH_MOVIE_ID
import model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMoviesApi {
    @GET("3/movie/popular?$DEFAULT_QUERY")
    fun listPopular(): Call<ListaPaginada>

    @GET("3/movie/{$PATH_MOVIE_ID}?$DEFAULT_QUERY")
    fun getMovieById(@Path(PATH_MOVIE_ID)id:Int): Call<MovieModel>
}
object ApiConsts{
private const val API_KEY = "64d1d2e3bea125434340a14f37f603a2"
const val  PATH_MOVIE_ID = "id"
const val  DEFAULT_QUERY = "api_key=$API_KEY&language=en-US&page=1"
}

class ListaPaginada(val page:Int, val results: List<MovieModel>)