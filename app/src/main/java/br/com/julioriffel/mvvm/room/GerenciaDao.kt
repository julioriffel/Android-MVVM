package br.com.julioriffel.mvvm.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.julioriffel.mvvm.model.Gerencia

@Dao
interface GerenciaDao {
    @Query(value = "SELECT * FROM Gerencia")
    fun getAll(): LiveData<List<Gerencia>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gerencia: Gerencia)
}