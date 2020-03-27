package com.example.myservice

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    private val mList: MutableList<People> = mutableListOf()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    private val mBinder = object : IMyAidlInterface.Stub() {
        override fun getList(): MutableList<People> {
            return mList
        }

        override fun add(people: People?) {
            people?.apply {
                mList.add(this)
            }
        }

    }
}