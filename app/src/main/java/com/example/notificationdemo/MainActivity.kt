
package com.example.notificationdemo
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    private val channelID = "com.example.notificationdemo.channel1"
    private var notificationManager:NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channelID, "DemoChannel", "this is a demo")
        button = findViewById(R.id.button)
        button.setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification() {
        val notificationId = 45
        val notification = NotificationCompat.Builder(
            this@MainActivity, channelID
        ).setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        // 오레오 버전 이상에서만 사용
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Determines how to interrupt the user for any notification that belongs to this channel.
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id,name,importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}