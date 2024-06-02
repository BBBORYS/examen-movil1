package com.borys.moviles_examen.ui.evento

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.borys.moviles_examen.R
import com.borys.moviles_examen.data.evento.Evento
import com.borys.moviles_examen.ui.AppViewModelProvider
import com.borys.moviles_examen.ui.navegation.NavigationController

object EventoDestination : NavigationController {
    override val route = "evento"
    override val titleRes = R.string.cliente_title
}

@Composable
fun Screen(
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EventoViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val eventoUiState by viewModel.clienteUiState.collectAsState()

    Scaffold(
        modifier = modifier,
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Body(
                eventoList = eventoUiState.eventoList,
                onItemClick = navigateToItemUpdate,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp)
            )
        }
    }
}


@Composable
private fun Body(
    eventoList: List<Evento>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        val contentColor = Color(0xFF020101)

        Text(
            text = "Evento",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
            color = contentColor,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        List(
            eventoList = eventoList,
            onItemClick = { onItemClick(it.eventoID) },
            contentPadding = contentPadding,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        )
    }

}

@Composable
private fun List(
    eventoList: List<Evento>,
    onItemClick: (Evento) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = eventoList, key = { it.eventoID }) { evento ->
            Info(
                evento = evento,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(evento) }
            )
        }
    }
}

@Composable
fun Info(
    evento: Evento,
    modifier: Modifier = Modifier
) {
    val cardBackgroundColor = Color(0xFF45D134)
    val contentColor = Color(0xFF1C1C1C)

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = evento.imagenId),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                DetailText(label = "ID: ${evento.eventoID}", color = contentColor)
                DetailText(label = "Nombre: ${evento.nombre}", color = contentColor)
                DetailText(label = "Email: ${evento.email}", color = contentColor)
                DetailText(label = "Dirección: ${evento.direccion}", color = contentColor)
                DetailText(label = "Teléfono: ${evento.telefono}", color = contentColor)
            }
        }
    }
}

@Composable
fun DetailText(label: String, color: Color) {
    Text(
        text = label,
        color = color,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(vertical = 2.dp)
    )
}
