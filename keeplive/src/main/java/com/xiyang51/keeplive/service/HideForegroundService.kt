package com.xiyang51.keeplive.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.xiyang51.keeplive.KeepLive
import com.xiyang51.keeplive.config.NotificationUtils
import com.xiyang51.keeplive.receiver.NotificationClickReceiver

class HideForegroundService : Service() {

    private var handler: android.os.Handler? = null
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        startForeground()
        if (handler == null) {
            handler = Handler()
        }
        handler!!.postDelayed({
            stopForeground(true)
            stopSelf()
        }, 2000)
        return Service.START_NOT_STICKY
    }


    private fun startForeground() {
        if (KeepLive.foregroundNotification != null) {
            val intent = Intent(applicationContext, NotificationClickReceiver::class.java)
            intent.action = NotificationClickReceiver.CLICK_NOTIFICATION
            val notification = NotificationUtils.createNotification(this, KeepLive.foregroundNotification!!.getTitle(), KeepLive.foregroundNotification!!.getDescription(), KeepLive.foregroundNotification!!.getIconRes(), intent)
            startForeground(13691, notification)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}