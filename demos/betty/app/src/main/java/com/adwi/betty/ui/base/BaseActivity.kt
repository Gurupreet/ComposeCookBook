package com.adwi.betty.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.work.*
import com.adwi.betty.work.FetchFreshDataWork
import com.adwi.betty.work.SetupNotificationsWork
import java.util.*
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalFoundationApi::class)
@OptIn(ExperimentalMaterialApi::class)
@OptIn(ExperimentalAnimationApi::class)
abstract class BaseActivity : AppCompatActivity() {

    protected abstract val viewModel: BaseViewModel?

    var workId: UUID? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContent()
        createSetupNotificationsWork()
        createFetchFreshDataWork()

    }

    abstract fun setupContent()
    abstract fun workProgress(id: UUID)

    @SuppressLint("IdleBatteryChargingConstraints")
    fun createFetchFreshDataWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .build()

        val fetchWork = PeriodicWorkRequestBuilder<FetchFreshDataWork>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        workId = fetchWork.id

        WorkManager
            .getInstance(this)
            .enqueueUniquePeriodicWork(
                TAG_ODDS_UPDATE,
                ExistingPeriodicWorkPolicy.KEEP,
                fetchWork
            )
        workProgress(fetchWork.id)
        Log.d(TAG, "Created FetchFreshDataWork")
    }

    @SuppressLint("IdleBatteryChargingConstraints")
    fun createSetupNotificationsWork(
    ) {
        val notificationWork = OneTimeWorkRequest.from(SetupNotificationsWork::class.java)

        WorkManager
            .getInstance(this)
            .enqueueUniqueWork(
                TAG_SETUP_NOTIFICATIONS,
                ExistingWorkPolicy.KEEP,
                notificationWork
            )

        Log.d(TAG, "Created work: $TAG_SETUP_NOTIFICATIONS")
    }
}

private const val TAG = "MainActivity"
private const val TAG_ODDS_UPDATE = "tag_odds_update"
const val TAG_SETUP_NOTIFICATIONS = "tag_setup_notifications"