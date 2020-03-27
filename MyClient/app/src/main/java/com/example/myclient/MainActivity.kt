package com.example.myclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.myservice.IMyAidlInterface
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mAidl: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bind()
        btn.setOnClickListener {
            mAidl?.add(People("名字", (0..10).random()))
            tv.text = mAidl?.list.toString()
        }

    }

    private fun bind() {
        val intent = Intent()
        intent.component = ComponentName("com.example.myservice", "com.example.myservice.MyService")
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mAidl = IMyAidlInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mAidl = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(mServiceConnection)
    }
}
