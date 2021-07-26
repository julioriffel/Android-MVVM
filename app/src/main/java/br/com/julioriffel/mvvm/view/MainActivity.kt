package br.com.julioriffel.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.julioriffel.mvvm.adapter.GerenciaAdapter
import br.com.julioriffel.mvvm.databinding.ActivityMainBinding
import br.com.julioriffel.mvvm.model.Gerencia
import br.com.julioriffel.mvvm.viewmodel.GerenciaViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var gerenciaViewModel: GerenciaViewModel
    private val adapter by lazy { GerenciaAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gerenciaViewModel = ViewModelProvider(this).get(GerenciaViewModel::class.java)

        binding.rv.adapter = adapter
        initListeners()
        getAll()
    }

    private fun initListeners() {
        binding.fab.setOnClickListener {
            gerenciaViewModel.insert(this, Gerencia(id = 0, nome = "ASD"))
        }

        adapter.listenerClick = {
            Toast.makeText(this, it.nome, Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, AddCartaoActivity::class.java))
        }
    }

    private fun getAll() {
        gerenciaViewModel.getAll(this)!!.observe(this, Observer {
            adapter.submitList(it)
        })
    }

}