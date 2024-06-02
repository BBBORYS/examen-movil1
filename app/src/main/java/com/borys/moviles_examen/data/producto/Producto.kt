package com.borys.moviles_examen.data.producto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.borys.moviles_examen.data.evento.Evento

@Entity(
    tableName = "Producto",
    foreignKeys = [ForeignKey(
        entity = Evento::class,
        parentColumns = ["eventoID"],
        childColumns = ["eventoPropietarioID"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val cantidadDisponible: Int,
    val eventoPropietarioID: Int
)
