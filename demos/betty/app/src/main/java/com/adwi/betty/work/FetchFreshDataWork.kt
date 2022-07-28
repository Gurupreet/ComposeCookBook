package com.adwi.betty.work

import android.content.Context
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterialApi
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.adwi.betty.data.repository.OddsRepository
import com.adwi.betty.data.repository.fullSportList
import com.adwi.betty.util.Channel
import com.adwi.betty.util.NotificationUtil
import com.adwi.betty.util.round
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalMaterialApi::class)
@OptIn(ExperimentalFoundationApi::class)
@HiltWorker
class FetchFreshDataWork @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val notificationUtil: NotificationUtil,
    private val repository: OddsRepository
) : CoroutineWorker(context, params) {

    companion object {
        const val Progress = "Progress"
    }

    override suspend fun doWork(): Result {
        return try {
            repository.deleteAllOdds()

            fullSportList.forEachIndexed { index, sport ->
                repository.fetchOddsRemote(sportKey = sport)
                val data = workDataOf(Progress to index)
                setProgress(data)
            }

            val odd = repository.getOddsLocal().first()[0]

            val winAmount = (5 * odd.difference).round(2)

            notificationUtil.sendNotification(
                id = 1,
                channel = Channel.ODDS_UPDATE,
                title = "Betty updated best odds!",
                message = "Check out this best odd!",
                expandedMessage = "${odd.teamAName} vs ${odd.teamBName} \n${odd.teamAPrice} to ${odd.teamBPrice} \nPut bet for £5 and win £$winAmount"
            )

            Result.success()
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
            Result.failure()
        }
    }
}

private const val TAG = "FetchFreshDataWork"