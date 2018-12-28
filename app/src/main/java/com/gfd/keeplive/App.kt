package com.gfd.keeplive

import android.app.Application
import android.content.Context
import android.content.Intent
import com.xiyang51.keeplive.KeepLive
import com.xiyang51.keeplive.config.ForegroundNotification
import com.xiyang51.keeplive.config.ForegroundNotificationClickListener
import com.xiyang51.keeplive.config.KeepLiveService

class App : Application() {

    override fun onCreate() {
        super.onCreate()
KeepLive.startWork(this, KeepLive.RunMode.ROGUE, ForegroundNotification("Title", "message",
        R.mipmap.ic_launcher, object : ForegroundNotificationClickListener {
    override fun foregroundNotificationClick(context: Context, intent: Intent) {
        //点击通知回调

    }
}), object : KeepLiveService {
    override fun onStop() {
        //可能调用多次，跟onWorking匹配调用
    }

    override fun onWorking() {
        //一直存活，可能调用多次
    }
})
    }
}