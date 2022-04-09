package com.revature.expiration_date.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.revature.expiration_date.BottomNavBar
import com.revature.expiration_date.R

const val INTENT_KEY_MESSAGE = "msg"

class ExpiredNotification: Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val result = super.onStartCommand(intent, flags, startId)
        Log.i("NOTIFICATION", "onStartCommand")

        val msg = intent?.getStringExtra(INTENT_KEY_MESSAGE)
        showNotification(msg ?: "")
        return result
    }

    private fun showNotification(msg: String) {
        Log.i("NOTIFICATION", "Called showNotification")
        createNotificationChannel()

        val toProductView = Intent(this, BottomNavBar("view")::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val toSendMessage = Intent(this, BottomNavBar("message")::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val tapIntent: PendingIntent = PendingIntent.getActivity(this, 2, toProductView, PendingIntent.FLAG_IMMUTABLE)
        val actionIntent: PendingIntent = PendingIntent.getActivity(this, 0, toSendMessage, PendingIntent.FLAG_IMMUTABLE)

        with(NotificationCompat.Builder(this, "Channel ID")) {
            setSmallIcon(R.drawable.ic_pantry)
            setContentTitle("Expired Items!")
            setContentText("It's time to replace some items")
            setStyle(
                NotificationCompat.BigTextStyle().bigText(msg)
            )
            priority = NotificationCompat.PRIORITY_DEFAULT
            setContentIntent(tapIntent)
            addAction(0, "SEND SHOPPING LIST", actionIntent)
            setAutoCancel(true)
            startForeground(1, build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    "Channel ID",
                    "Channel Name",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply { description = "Channel Description" }
            )
        }
    }
}