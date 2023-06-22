package com.github.user.newsapp.ui.component

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.user.newsapp.ui.screen.HomeFragment
import com.github.user.newsapp.ui.screen.ui.theme.ProfileFragment
import com.github.user.newsapp.ui.theme.NewsAppTheme

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, "home"),
        BottomNavItem("Profile", Icons.Default.Person, "profile")
    )
    var selectedItem by remember { mutableStateOf(items[0]) }

    Scaffold(bottomBar = {
        BottomNavigation {
            items.forEach { item ->
                BottomNavigationItem(selected = selectedItem == item, onClick = {
                    selectedItem = item
                    navController.navigate(item.route)
                }, label = { Text(text = item.label) }, icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = item.label
                    )
                })
            }
        }
    }, content = {
        NavHost(
            navController = navController as NavHostController,
            startDestination = items[0].route
        ) {
            composable(items[0].route) { HomeFragment() }
            composable(items[1].route) { ProfileFragment() }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        val navController = rememberNavController()
        BottomNavBar(navController)
    }
}

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)