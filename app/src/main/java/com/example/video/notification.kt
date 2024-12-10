package com.example.video

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class notificationBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = List<ActivityManager.RunningTaskInfo>(Int.MAX_VALUE)
        if (!tasks.isEmpty()) {
            val tasksSize = tasks.size
            for (i in 0 until tasksSize) {
                val taskinfo = tasks[i]
                if (taskinfo.topActivity!!.packageName == context.applicationContext.packageName) {
                    am.moveTaskToFront(taskinfo.id, 0)
                }
            }
        }
    }
}
