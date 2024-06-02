package com.borys.moviles_examen.data.evento

import com.borys.moviles_examen.data.evento.interfaces.EventoDao
import com.borys.moviles_examen.data.evento.interfaces.EventoRepository
import kotlinx.coroutines.flow.Flow

class OfflineEventoRepository(private val eventoDao: EventoDao) : EventoRepository {

        override fun obtenerTodosLosEvento(): Flow<List<Evento>> = eventoDao.obtenerTodosLosEvento()

        override fun obtenerEventoPorId(id: Int): Evento? = eventoDao.obtenerEventoPorId(id)

        override suspend fun agregarEvento(evento: Evento) = eventoDao.agregarEvento(evento)

        override suspend fun delete(evento: Evento) = eventoDao.delete(evento)

        override suspend fun update(evento: Evento) = eventoDao.update(evento)

}
