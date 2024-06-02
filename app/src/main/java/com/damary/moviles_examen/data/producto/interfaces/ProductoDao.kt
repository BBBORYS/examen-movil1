package com.borys.moviles_examen.data.producto.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.borys.moviles_examen.data.producto.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {

    @Insert
    fun agregarProducto(producto: Producto)

    @Update
    suspend fun update(producto: Producto)

    @Delete
    suspend fun delete(producto: Producto)

    @Query("SELECT * FROM Producto WHERE id = :id")
    fun obtenerProductoPorId(id: Int): Producto?

    @Query("SELECT * FROM Producto")
    fun obtenerTodosLosProductos(): Flow<List<Producto>>
}
