package com.borys.moviles_examen.ui.evento.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borys.moviles_examen.data.evento.interfaces.EventoRepository
import com.borys.moviles_examen.data.producto.Producto
import com.borys.moviles_examen.data.producto.interfaces.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoEntryViewModel(
    private val productoRepository: ProductoRepository
) : ViewModel() {

    private val _productoUiState = MutableStateFlow(ProductoUiState())
    val productoUiState: StateFlow<ProductoUiState> = _productoUiState

    init {
        loadproductos()
    }

    private fun loadproductos() {
        viewModelScope.launch {
            _productoUiState.value = _productoUiState.value.copy(isLoading = true)
            try {
                productoRepository.obtenerTodosLosProductos().collect { productos ->
                    _productoUiState.value = ProductoUiState(productos = productos)
                }
            } catch (e: Exception) {
                _productoUiState.value = _productoUiState.value.copy(errorMessage = e.message)
            } finally {
                _productoUiState.value = _productoUiState.value.copy(isLoading = false)
            }
        }
    }

    data class ProductoUiState(
        val productos: List<Producto> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

}
