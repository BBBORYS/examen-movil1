package com.borys.moviles_examen.data.evento.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.borys.moviles_examen.data.evento.Evento
import kotlinx.coroutines.flow.Flow

@Dao
interface EventoDao {
    @Insert
    fun agregarEvento(evento: Evento)

    @Update
    suspend fun update(evento: Evento)

    @Delete
    suspend fun delete(evento: Evento)

    @Query("SELECT * FROM Evento WHERE eventoID = :id")
    fun obtenerEventoPorId(id: Int): Evento?

    @Query("SELECT * FROM Evento")
    fun obtenerTodosLosEvento(): Flow<List<Evento>>
}
