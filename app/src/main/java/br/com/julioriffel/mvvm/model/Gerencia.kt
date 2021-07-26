package br.com.julioriffel.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Gerencia(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
)
