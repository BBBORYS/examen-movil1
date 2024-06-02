package com.borys.moviles_examen.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.borys.moviles_examen.data.evento.Evento
import com.borys.moviles_examen.data.evento.interfaces.EventoDao
import com.borys.moviles_examen.data.producto.Producto
import com.borys.moviles_examen.data.producto.interfaces.ProductoDao

@Database(
    entities = [
        Evento::class,
        Producto::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EventoDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao
    abstract fun eventoDao(): EventoDao

    companion object {
        @Volatile
        private var INSTANCE: EventoDatabase? = null

        fun getDatabase(context: Context): EventoDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    EventoDatabase::class.java,
                    "evento"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
