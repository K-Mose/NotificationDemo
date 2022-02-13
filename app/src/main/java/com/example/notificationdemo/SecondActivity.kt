package com.example.notificationdemo

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        receiveInput()
    }

    private fun receiveInput() {
        val KEY_REPLY = "key_reply"
        val intent:Intent = this@SecondActivity.intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if(remoteInput!=null) {
            val inputString = remoteInput.getCharSequence(KEY_REPLY).toString()
            findViewById<TextView>(R.id.tv_result).apply {
                text = inputString
            }
            val channelID = "com.example.notificationdemo.channel1"
            val notificationId = 45

            val repliedNotification = NotificationCompat.Builder(this@SecondActivity, channelID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("You reply received")
                .build()
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, repliedNotification)
        }

    }
}