/*
 * Copyright (c) 2021.
 * Julio Cezar Riffel<julioriffel@gmail.com>
 */

package br.com.julioriffel.mvvm.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.julioriffel.mvvm.model.Gerencia
import br.com.julioriffel.mvvm.repository.GerenciaRepository
import com.br.natanfc.filmesflix.api.MovieRestApiTask

class GerenciaViewModel : ViewModel() {

    companion object {
        const val TAG = "GerenciaViewModel"
    }

    private val movieRestApiTask = MovieRestApiTask()


    private val movieRepository = GerenciaRepository(movieRestApiTask)
    private var _moviesList = MutableLiveData<List<Gerencia>>()
    val moviesList: LiveData<List<Gerencia>>
        get() = _moviesList

    fun getAllMovies(context: Context) {
        Thread {
            try {
                _moviesList.postValue(movieRepository.getAllMovies(context))


            } catch (exception: Exception) {
                Log.d(TAG, exception.message.toString())
            }
        }.start()
    }


    var liveDataLogin: LiveData<List<Gerencia>>? = null

    fun insert(context: Context, gerencia: Gerencia) {
        GerenciaRepository.insert(context, gerencia)
    }

    fun getAll(context: Context): LiveData<List<Gerencia>>? {
        liveDataLogin = GerenciaRepository.getAll(context)
        return liveDataLogin
    }

    fun init(context: Context) {

        getAllMovies(context)

    }


}