package br.com.dio.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.com.dio.App
import br.com.dio.R
import br.com.dio.data.BusinessCard
import br.com.dio.databinding.ActivityAddBusinessBinding

class AddBusinessActivity : AppCompatActivity() {

    // Nova forma recomendada pelo google para inflar a view
    private val binding by lazy { ActivityAddBusinessBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }

        binding.btnConfirmar.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.titName.editText?.text.toString(),
                telefone = binding.titTefone.editText?.text.toString(),
                email = binding.titEmail.editText?.text.toString(),
                fundoPersonalizado = binding.titCor.editText?.text.toString(),
                empresa = binding.titEmpresa.editText?.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success, Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}