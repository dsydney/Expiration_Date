package com.revature.expiration_date.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.revature.expiration_date.R
import com.revature.expiration_date.SendMessageScreen

class ExpiredNotification: Service() {
    override fun onBind(p0: Intent?): IBinder? = null

    private fun showNotification() {
        val toProductView = Intent(this, ExpiredNotification::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
//        val toSendMessage = Intent(this, SendMessage::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
        val tapIntent: PendingIntent = PendingIntent.getActivity(this, 0, toProductView, PendingIntent.FLAG_IMMUTABLE)
//        val actionIntent: PendingIntent = PendingIntent.getActivity(this, 0, toProductView, PendingIntent.FLAG_IMMUTABLE)

        var builder = NotificationCompat.Builder(this, "")
            .setSmallIcon(R.drawable.ic_pantry)
            .setContentTitle("Expired Items!")
            .setContentText("It's time to replace some items...")
            .setStyle(NotificationCompat.BigTextStyle().bigText("This item\nThat item\nAnother one"))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(tapIntent)
//            .addAction(0, "SEND SHOPPING LIST", actionIntent)
            .setAutoCancel(true)
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