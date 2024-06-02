package com.borys.moviles_examen.ui.evento

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.borys.moviles_examen.R
import com.borys.moviles_examen.data.evento.Evento
import com.borys.moviles_examen.data.evento.interfaces.EventoRepository
import com.borys.moviles_examen.data.producto.Producto
import com.borys.moviles_examen.data.producto.interfaces.ProductoRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventoViewModel(private val eventoRepository: EventoRepository,
                       private val productoRepository: ProductoRepository): ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    private val insercion_imagen = listOf(
        R.drawable.imagen1,
        R.drawable.imagen2,
        R.drawable.imagen3,
        R.drawable.imagen4,
        R.drawable.imagen5,
    )

    private val evento = listOf(
        Evento(nombre = "Andres mateo", email = "alejandro.gomez@example.com", direccion = "Calle Mayor, 125", telefono = "+34 912 345 678", imagenId = insercion_imagen[0]),
        Evento(nombre = "Ana lucia", email = "laura.fernandez@example.com", direccion = "Avenida de América, 332", telefono = "+34 931 234 567", imagenId = insercion_imagen[1]),
        Evento(nombre = "Matilda gabriela", email = "pedro.martinez@example.com", direccion = "Plaza del Sol, 5", telefono = "+34 954 321 654", imagenId = insercion_imagen[2]),
        Evento(nombre = "Marta Adrade", email = "marta.lopez@example.com", direccion = "Calle de la Luna, 21", telefono = "+34 961 234 567", imagenId = insercion_imagen[3]),
        Evento(nombre = "Javier Rodríguez", email = "javier.rodriguez@example.com", direccion = "Avenida de la Constitución", telefono = "+34 944 567 890", imagenId = insercion_imagen[4])
    )

    private val productos = listOf(
        Producto(nombre = "andres mateo", descripcion = "requiere un evento de improvisado.", precio = 150.00, cantidadDisponible = 10, eventoPropietarioID = 1),
        Producto(nombre = "evento", descripcion = "requiere un dia de feria la sala 3.", precio = 800.00, cantidadDisponible = 20, eventoPropietarioID = 2),
        Producto(nombre = "Liam ", descripcion = "se queja .", precio = 1200.00, cantidadDisponible = 5, eventoPropietarioID = 3),
        Producto(nombre = "Sofía ", descripcion = "no le gusta el servicio.", precio = 500.00, cantidadDisponible = 15, eventoPropietarioID = 4),
        Producto(nombre = "Isabella ", descripcion = "requiere rebaja.", precio = 100.00, cantidadDisponible = 25, eventoPropietarioID = 5),
        Producto(nombre = "Carlos jose", descripcion = "pago por adelantado el local.", precio = 200.00, cantidadDisponible = 30, eventoPropietarioID = 1),
        Producto(nombre = "Noah ", descripcion = "pide que le den otra fecha.", precio = 250.00, cantidadDisponible = 12, eventoPropietarioID = 2),
        Producto(nombre = "Ava 3D", descripcion = "la sala le parecio pequeño.", precio = 600.00, cantidadDisponible = 8, eventoPropietarioID = 3),
        Producto(nombre = "William ", descripcion = "quiere otro evento pronto.", precio = 350.00, cantidadDisponible = 18, eventoPropietarioID = 4),
        Producto(nombre = "James  ", descripcion = "solo pide que le abran el evento mas temprano", precio = 120.00, cantidadDisponible = 40, eventoPropietarioID = 5),
        Producto(nombre = "Benjamin", descripcion = "requiere que el evento tenga red wifi.", precio = 180.00, cantidadDisponible = 22, eventoPropietarioID = 1),
        Producto(nombre = "Amelia ", descripcion = "requiere que el evento tenga caraoke.", precio = 300.00, cantidadDisponible = 14, eventoPropietarioID = 2),
        Producto(nombre = "Lucas ", descripcion = "requiere que el evento se pueda correr.", precio = 90.00, cantidadDisponible = 35, eventoPropietarioID = 3),
        Producto(nombre = "Henry", descripcion = "requiere que el evento tenga agua de ser el caso.", precio = 150.00, cantidadDisponible = 19, eventoPropietarioID = 4),
        Producto(nombre = "Evelyn ", descripcion = "requiere que el local se pueda proyectar.", precio = 700.00, cantidadDisponible = 10, eventoPropietarioID = 5)
    )

    init {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }

        viewModelScope.launch(handler) {
            val eventoList = eventoRepository.obtenerTodosLosEvento().firstOrNull()
            if (eventoList == null || eventoList.isEmpty()) {
                try {
                    evento.forEach { evento ->
                        withContext(Dispatchers.IO) {
                            eventoRepository.agregarEvento(evento)
                        }
                    }
                } catch (e: Exception) {
                    println("Error al insertar evento: ${e.message}")
                }
            }
        }

        viewModelScope.launch(handler) {
            val productosList = productoRepository.obtenerTodosLosProductos().firstOrNull()
            if (productosList == null || productosList.isEmpty()) {
                try {
                    productos.forEach { producto ->
                        withContext(Dispatchers.IO) {
                            productoRepository.agregarProducto(producto)
                        }
                    }
                } catch (e: Exception) {
                    println("Error al insertar el evento: ${e.message}")
                }
            }
        }
    }


    val clienteUiState: StateFlow<EventoUiState> =
        eventoRepository.obtenerTodosLosEvento().map { evento ->
            EventoUiState(evento)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = EventoUiState()
            )
}


data class EventoUiState(val eventoList: List<Evento> = listOf())
