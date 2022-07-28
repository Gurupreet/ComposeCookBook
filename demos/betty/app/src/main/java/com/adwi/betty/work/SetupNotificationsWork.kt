package com.adwi.betty.work

import android.content.Context
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterialApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.adwi.betty.util.NotificationUtil
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalMaterialApi::class)
@OptIn(ExperimentalFoundationApi::class)
@HiltWorker
class SetupNotificationsWork @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val notificationUtil: NotificationUtil
) : Worker(context, params) {

    override  fun doWork(): Result {
        return try {

            notificationUtil.setupNotifications()

            Log.d(TAG, "SetupNotificationsWork success")
            Result.success()
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            Result.failure()
        }
    }
}

private const val TAG = "Work SetupNotifications"