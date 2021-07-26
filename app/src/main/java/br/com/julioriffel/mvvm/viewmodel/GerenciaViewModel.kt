package br.com.julioriffel.mvvm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.julioriffel.mvvm.model.Gerencia
import br.com.julioriffel.mvvm.repository.GerenciaRepository

class GerenciaViewModel : ViewModel() {

    var liveDataLogin: LiveData<List<Gerencia>>? = null

    fun insert(context: Context, gerencia: Gerencia) {
        GerenciaRepository.insert(context, gerencia)
    }

    fun getAll(context: Context): LiveData<List<Gerencia>>? {
        liveDataLogin = GerenciaRepository.getAll(context)
        return liveDataLogin
    }

}