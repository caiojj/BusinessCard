package br.com.dio.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.com.dio.App
import br.com.dio.databinding.ActivityMainBinding
import br.com.dio.util.Image

class MainActivity : AppCompatActivity() {

    private val bindind by lazy {ActivityMainBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindind.root)
        bindind.rvCards.adapter = adapter
        getAllBusnessCard()
        insertListeners()
    }

    private fun insertListeners() {
        bindind.btnAdicionar.setOnClickListener {
            // intent de "Intenção" de abrir algo
            // this de contexto de onde a gente esta, ou seja a MainActicity
            val intent = Intent(this@MainActivity, AddBusinessActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerShere = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    private fun getAllBusnessCard() {
        mainViewModel.getAll().observe(this, { busnessCard ->
            adapter.submitList(busnessCard)
        })
    }
}