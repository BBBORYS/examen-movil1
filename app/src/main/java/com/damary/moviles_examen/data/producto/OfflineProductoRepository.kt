package com.borys.moviles_examen.data.producto

import com.borys.moviles_examen.data.producto.interfaces.ProductoDao
import com.borys.moviles_examen.data.producto.interfaces.ProductoRepository
import kotlinx.coroutines.flow.Flow


class OfflineProductoRepository (private val productodao: ProductoDao) : ProductoRepository {

    override fun obtenerProductoPorId(id: Int): Producto? = productodao.obtenerProductoPorId(id)

    override fun obtenerTodosLosProductos(): Flow<List<Producto>> = productodao.obtenerTodosLosProductos()

    override fun agregarProducto(producto: Producto) = productodao.agregarProducto(producto)

    override suspend fun update(producto: Producto) = productodao.update(producto)

    override suspend fun delete(producto: Producto) = productodao.delete(producto)

}