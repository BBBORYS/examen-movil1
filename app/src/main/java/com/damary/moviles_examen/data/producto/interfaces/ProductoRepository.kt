package com.borys.moviles_examen.data.producto.interfaces


import com.borys.moviles_examen.data.producto.Producto
import kotlinx.coroutines.flow.Flow

interface ProductoRepository {

    fun obtenerProductoPorId(id: Int): Producto?

    fun obtenerTodosLosProductos(): Flow<List<Producto>>

    fun agregarProducto(producto: Producto)

    suspend fun update(producto: Producto)

    suspend fun delete(producto: Producto)
}