package com.borys.moviles_examen.ui.evento.productos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.borys.moviles_examen.R
import com.borys.moviles_examen.ui.AppViewModelProvider
import com.borys.moviles_examen.ui.navegation.NavigationController
import com.borys.moviles_examen.data.producto.Producto

object ProductoDestination : NavigationController {
    override val route = "envio_detalle"
    override val titleRes = R.string.producto_titulo
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    envioId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProductoEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val productoUiState by viewModel.productoUiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DETALLES DEL EVENTO") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                productoUiState.isLoading -> {
                    LoadingState()
                }
                productoUiState.errorMessage != null -> {
                    ErrorState(errorMessage = productoUiState.errorMessage!!)
                }
                else -> {
                    List(
                        detallesEnvio = productoUiState.productos,
                        envioId = envioId,
                    )
                }
            }
        }
    }
}

@Composable
fun List(
    detallesEnvio: List<Producto>,
    envioId: Int,
) {
    val detallesFiltrados = detallesEnvio.filter { it.eventoPropietarioID == envioId }
    LazyColumn(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        items(detallesFiltrados.size) { index ->
            Data(detallesFiltrados[index])
        }
    }
}
@Composable
fun Data(producto: Producto) {
    val cardBackgroundColor = Color(0xFF56D512)
    val textColor = Color(0xFF020101)

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Detail_Text(label = "ID: ${producto.id}", color = textColor)
                Detail_Text(label = "Propietario ID: ${producto.eventoPropietarioID}", color = textColor)
            }
            Detail_Text(label = "Nombre: ${producto.nombre}", color = textColor)
            Detail_Text(label = "Descripción: ${producto.descripcion}", color = textColor)
            Detail_Text(label = "Precio: ${producto.precio}", color = textColor)
            Detail_Text(label = "Cantidad Disponible: ${producto.cantidadDisponible}", color = textColor)
        }
    }
}

@Composable
fun LoadingState() {
    Text(
        text = "Cargando...",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}

@Composable
fun Detail_Text(label: String, color: Color) {
    Text(
        text = label,
        color = color,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
@Composable
fun ErrorState(errorMessage: String) {
    Text(
        text = "Error: $errorMessage",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}
