package com.adwi.betty.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color.RED
import android.media.AudioAttributes
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.widget.RemoteViews
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.adwi.betty.R
import com.adwi.betty.ui.main.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

enum class Channel { ODDS_UPDATE }

@OptIn(ExperimentalFoundationApi::class)
@OptIn(ExperimentalMaterialApi::class)
@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("NewApi")
class NotificationUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private lateinit var channelId: String

    private val bettyGroupId = "betty_group"

    private val runningOOrLater =
        android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O

    private val runningSOrLater =
        android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S


    fun setupNotifications() {
        if (runningOOrLater) {
            val bettyGroupName = context.getString(R.string.app_name)

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannelGroup(
                NotificationChannelGroup(
                    bettyGroupId,
                    bettyGroupName
                )
            )
            createNotificationChannel(Channel.ODDS_UPDATE)
        }
    }

    private fun createNotificationChannel(channel: Channel) {
        if (runningOOrLater) {
            var name = ""
            var importance = 0
            val channelGroup: String
            when (channel) {
                Channel.ODDS_UPDATE -> {
                    channelId = "betty_odds_update"
                    name = "Odds update"
                    importance = NotificationManager.IMPORTANCE_DEFAULT
                    channelGroup = bettyGroupId
                }
            }
            val ringtoneManager = getDefaultUri(TYPE_NOTIFICATION)
            val audioAttributes =
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
            val notificationChannel = NotificationChannel(channelId, name, importance)
            notificationChannel.apply {
                group = channelGroup
                enableLights(true)
                lightColor = RED
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                setSound(ringtoneManager, audioAttributes)
            }
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun sendNotification(
        id: Int,
        channel: Channel,
        title: String,
        message: String,
        expandedMessage: String
    ) {
        val requestCode = 1
        val intentDestination: Class<*>

        val smallBitmap =
            BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)

        when (channel) {
            Channel.ODDS_UPDATE -> {
                intentDestination = MainActivity::class.java
                notification.setStyle(NotificationCompat.BigTextStyle().bigText(message))
                    .setContentTitle(title)
                    .setContentText(message)
                    .setLargeIcon(smallBitmap)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_EVENT)
                    .setStyle(NotificationCompat.BigTextStyle()
                        .bigText(expandedMessage)
                    )
            }
        }

        val intent = Intent(context, intentDestination).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = if (runningSOrLater) {
             PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_ONE_SHOT)
        }

        notification.setContentIntent(pendingIntent)
            .setGroup(GROUP_NAME)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(id, notification.build())
        }
    }
}

private const val GROUP_NAME = "Betty"