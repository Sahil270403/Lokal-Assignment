package com.tempest.lokalassignment.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tempest.lokalassignment.presentation.screens.JobDetailPage
import com.tempest.lokalassignment.presentation.viewmodal.JobViewModal
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val viewModal = koinViewModel<JobViewModal>()

    NavHost(
        navController, startDestination = MainScreen
    ) {

        composable<MainScreen> {
            HomeNavigation(navController, viewModal)
        }

        composable<DetailScreen> { backStackEntry ->
            val job = backStackEntry.toRoute<DetailScreen>()
            JobDetailPage(navController, viewModal, job.id)
        }

    }
}

@Serializable
object MainScreen

@Serializable
object HomeScreen

@Serializable
object BookmarkScreen

@Serializable
data class DetailScreen(val id: Int)