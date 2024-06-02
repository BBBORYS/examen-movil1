package com.borys.moviles_examen.data.evento

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Evento")
data class Evento(
    val nombre: String,
    val email: String,
    val direccion: String,
    val telefono: String,
    val imagenId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var eventoID: Int = 0
}
