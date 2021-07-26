package br.com.julioriffel.mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import br.com.julioriffel.mvvm.model.Gerencia
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import br.com.julioriffel.mvvm.room.AppDatabase

import kotlinx.coroutines.Dispatchers.IO

class GerenciaRepository {

    companion object {

        var appDatabase: AppDatabase? = null

        var loginTableModel: LiveData<List<Gerencia>>? = null

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


}