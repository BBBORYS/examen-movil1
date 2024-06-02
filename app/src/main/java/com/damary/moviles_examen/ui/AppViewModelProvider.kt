package com.borys.moviles_examen.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.borys.moviles_examen.EventoApplication
import com.borys.moviles_examen.ui.evento.EventoViewModel
import com.borys.moviles_examen.ui.evento.productos.ProductoEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer { ProductoEntryViewModel(Clientespplication().container.productoRepository)
        }
        initializer { EventoViewModel(Clientespplication().container.eventoRepository,Clientespplication().container.productoRepository)
        }
}

fun CreationExtras.Clientespplication(): EventoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as EventoApplication)

}
