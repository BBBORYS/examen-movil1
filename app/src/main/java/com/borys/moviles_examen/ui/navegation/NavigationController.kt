package com.borys.moviles_examen.ui.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.borys.moviles_examen.ui.evento.EventoDestination
import com.borys.moviles_examen.ui.evento.Screen
import com.borys.moviles_examen.ui.evento.productos.Screen

import com.borys.moviles_examen.ui.evento.productos.ProductoDestination

@Composable
fun NavigationController(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = EventoDestination.route,
        modifier = modifier
    ) {
        composable(route = EventoDestination.route) {
            Screen(
                navigateToItemUpdate = { itemId ->
                    navController.navigate("${ProductoDestination.route}/$itemId")
                }
            )
        }
        composable(
            route = "${ProductoDestination.route}/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val itemId = navBackStackEntry.arguments?.getInt("itemId") ?: 0
            Screen(itemId, onBack= { navController.navigate(EventoDestination.route) })
        }
    }
}
