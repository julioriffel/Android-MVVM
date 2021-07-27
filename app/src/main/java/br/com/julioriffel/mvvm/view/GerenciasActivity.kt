/*
 * Copyright (c) 2021.
 * Julio Cezar Riffel<julioriffel@gmail.com>
 */

package br.com.julioriffel.mvvm.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.julioriffel.mvvm.adapter.GerenciaAdapter
import br.com.julioriffel.mvvm.databinding.ActivityGerenciasBinding
import br.com.julioriffel.mvvm.viewmodel.GerenciaViewModel

class GerenciasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGerenciasBinding
    lateinit var gerenciaViewModel: GerenciaViewModel
    private val adapter by lazy { GerenciaAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGerenciasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gerenciaViewModel = ViewModelProvider(this).get(GerenciaViewModel::class.java)
        gerenciaViewModel.init(this)
        binding.rvCards.adapter = adapter

        getAll()
    }

    private fun getAll() {
//
//        gerenciaViewModel.moviesList.observe(this, { list ->
//            if (list.isNotEmpty()) {
//                adapter.submitList(list)
//            }
//        })

        gerenciaViewModel.getAll(this)!!.observe(this, Observer {
            adapter.submitList(it)
        })//
//

    }
}