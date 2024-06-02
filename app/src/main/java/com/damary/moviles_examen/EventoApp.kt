package com.borys.moviles_examen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.borys.moviles_examen.ui.navegation.NavigationController

@Composable
fun EventoApp(navController: NavHostController = rememberNavController()) {
    NavigationController(navController = navController)
}

