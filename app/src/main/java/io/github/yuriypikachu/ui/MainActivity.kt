package io.github.yuriypikachu.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.yuriypikachu.handler.Looper
import io.github.yuriypikachu.handler.Message
import io.github.yuriypikachu.handler.R
import io.github.yuriypikachu.handler.YuriyHandler
import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        Looper.perpare();

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = object : YuriyHandler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                println(Thread.currentThread().name + ",received:" + msg.toString())
            }
        }
        for (i in 0..1) {
            Thread {
                while (true) {
                    val msg = Message()
                    msg.what = 1
                    synchronized(UUID::class.java) { msg.obj = currentThread().name + ",send message:" + UUID.randomUUID().toString() }
                    System.out.println(msg)
                    handler.sendMessage(msg)
                    try {
                        sleep(1000)
                    } catch (e: InterruptedException) {
                        // TODO: handle exception
                        e.printStackTrace()
                    }
                }
            }.start()
        }

        Looper.loop()
    }
}