package br.com.julioriffel.mvvm.room

import android.content.Context
import androidx.room.*
import br.com.julioriffel.mvvm.model.Gerencia

@Database(entities = arrayOf(Gerencia::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gerenciaDao() : GerenciaDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataseClient(context: Context) : AppDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}