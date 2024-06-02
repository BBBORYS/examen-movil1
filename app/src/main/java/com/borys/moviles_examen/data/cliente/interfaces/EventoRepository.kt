package com.borys.moviles_examen.data.evento.interfaces

import com.borys.moviles_examen.data.evento.Evento
import kotlinx.coroutines.flow.Flow

interface EventoRepository {

    fun obtenerTodosLosEvento(): Flow<List<Evento>>

    fun obtenerEventoPorId(id: Int): Evento?

    suspend fun agregarEvento(evento: Evento)

    suspend fun delete(evento: Evento)

    suspend fun update(evento: Evento)

}