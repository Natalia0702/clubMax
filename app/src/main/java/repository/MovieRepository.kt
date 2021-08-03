package repository

import com.example.clubmax.ListActivity
import com.example.clubmax.ListaPaginada
import model.MovieModel
import com.example.clubmax.TheMoviesApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    val retrofit: Retrofit
    val moviesApi: TheMoviesApi

    init {
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviede.org")
            .build()
        moviesApi = retrofit.create(TheMoviesApi::class.java)
    }

    fun getPopular(callback: (List<MovieModel>) -> Unit) {
        println("Caiu no getPopular")
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val call = moviesApi.listPopular()
                call.enqueue(object : Callback<ListaPaginada> {
                    override fun onResponse(
                        call: Call<ListaPaginada>,
                        response: Response<ListaPaginada>
                    ) {
                        println("Caiu no onresponse")
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<ListaPaginada>, t: Throwable) {
                        println("Caiu no onFailure")

                    }
                })
            }
        }
    }
}