package com.samtou.ipnet_gl3_2019.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.samtou.ipnet_gl3_2019.R

class ServiceActivity : AppCompatActivity() {

    lateinit var pendingIntent: PendingIntent
    lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        val intent = Intent(this, MonService::class.java)
        pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

//        onclick
    }

    fun startService(view: View) {
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 60*1000, pendingIntent)

    }
}
