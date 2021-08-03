package com.example.clubmax

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clubmax.databinding.ActivityListaBinding
import repository.MovieRepository


class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        setupList()
    }
    private fun initLayout(){
        binding = ActivityListaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupList(){
        val adapter = MovieDetailsAdapter({ id->
            openDetailsActivity(id)
        })
        binding.listItem.adapter = adapter
       MovieRepository.getPopular {
           adapter.addItemList(it)
       }

    }
private fun openDetailsActivity(id:Int){
    val intent = Intent(this, DetalhesActivity::class.java)
    intent.putExtra("chaveDoId",id)
    startActivity(intent)
}
}