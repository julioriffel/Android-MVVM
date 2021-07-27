/*
 * Copyright (c) 2021.
 * Julio Cezar Riffel<julioriffel@gmail.com>
 */

package br.com.julioriffel.mvvm.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import br.com.julioriffel.mvvm.model.Gerencia
import br.com.julioriffel.mvvm.room.AppDatabase
import com.br.natanfc.filmesflix.api.MovieRestApiTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class GerenciaRepository(private val movieRestApiTask: MovieRestApiTask) {

    companion object {

        const val TAG = "GerenciaRepository"

        var appDatabase: AppDatabase? = null

        var loginTableModel: LiveData<List<Gerencia>>? = null
        private val movieList = arrayListOf<Gerencia>()

        fun initializeDB(context: Context): AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        fun insert(context: Context, gerencia: Gerencia) {

            appDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                appDatabase!!.gerenciaDao().insert(gerencia)
            }

        }

        fun getAll(context: Context): LiveData<List<Gerencia>>? {

            appDatabase = initializeDB(context)
            loginTableModel = appDatabase!!.gerenciaDao().getAll()
            return loginTableModel
        }


    }

    fun getAllMovies(context: Context): List<Gerencia> {

        val request = movieRestApiTask.retrofitApi().getAllMovies().execute()

        if (request.isSuccessful) {
            request.body()?.let {
                it.forEach {
                    insert(context, it)
                }
                movieList.addAll(it)
            }
        } else {
            request.errorBody()?.let {
                Log.d(TAG, it.toString())
            }
        }

        return movieList
    }

}