package com.adwi.betty.ui.main

import android.os.Build
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.adwi.betty.ui.base.BaseActivity
import com.adwi.betty.ui.home.HomeScreen
import com.adwi.betty.ui.theme.BettyTheme
import com.adwi.betty.work.FetchFreshDataWork
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalMaterialApi::class)
@OptIn(ExperimentalFoundationApi::class)
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val viewModel: MainViewModel by viewModels()

    override fun setupContent() {
        setContent {
            ProvideWindowInsets {
                BettyTheme {
                    SetupNavigation()
                }
            }
        }
    }

    override fun workProgress(id: UUID) {
        WorkManager.getInstance(applicationContext)
            .getWorkInfoByIdLiveData(id)
            .observe(this, { info ->
                if (info != null) {
                    val progress = info.progress
                    val value = progress.getInt(FetchFreshDataWork.Progress, 0)

                    viewModel.setProgress(value)
                }
            })
    }

}

@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalFoundationApi::class)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.route
    ) {
        composable(NavScreen.HomeScreen.route) { backStackEntry ->
            val mainViewModel = hiltViewModel<MainViewModel>(backStackEntry)
            HomeScreen(viewModel = mainViewModel)
        }
    }
}

sealed class NavScreen(val route: String) {

    object HomeScreen : NavScreen("HomeScreen")
}