package com.borys.moviles_examen.data

import android.content.Context
import com.borys.moviles_examen.data.evento.OfflineEventoRepository
import com.borys.moviles_examen.data.evento.interfaces.EventoRepository
import com.borys.moviles_examen.data.producto.OfflineProductoRepository
import com.borys.moviles_examen.data.producto.interfaces.ProductoRepository

interface AppContainer {
    val eventoRepository: EventoRepository
    val productoRepository: ProductoRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val eventoRepository: EventoRepository by lazy {
        OfflineEventoRepository(EventoDatabase.getDatabase(context).eventoDao())
    }

    /**
     * Implementation for [HeladoDao]
     */
    override val productoRepository: ProductoRepository by lazy {
        OfflineProductoRepository(EventoDatabase.getDatabase(context).productoDao())
    }

}
