package com.example.clubmax.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.clubmax.MovieDetailsAdapter
import com.example.clubmax.databinding.ActivityDetalhesBinding
import com.example.clubmax.databinding.ActivityListaBinding
import model.repository.MovieRepository

class DetalhesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idMovie = intent.getIntExtra("chaveDoId", -1)

        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonReturn.setOnClickListener {
            val openDetails = Intent(this, MovieListActivity::class.java)
            startActivity(openDetails)
        }

        binding.shareIntent.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }


        //binding.tituloText.text = "Teste"

        MovieRepository.getMovie({
            binding.titleText.text = it.title
            binding.genero.text = it.genres.toString()
            binding.sinopse.text = it.overview
            binding.date.text = it.release_date
            binding.time.text = "${it.runtime} minute"
            binding.age.text = if (it.adult) "+18" else "-18"
            binding.starPref.text = it.vote_average.toString()
            binding.vote.text = it.vote_count.toString()

            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                .into(binding.poster)

        }, idMovie)
    }
}



