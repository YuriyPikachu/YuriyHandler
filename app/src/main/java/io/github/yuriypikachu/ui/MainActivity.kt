package io.github.yuriypikachu.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.github.yuriypikachu.handler.Looper
import io.github.yuriypikachu.handler.Message
import io.github.yuriypikachu.handler.R
import io.github.yuriypikachu.handler.YuriyHandler
import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import java.util.*

class MainActivity : AppCompatActivity() {

    val Tag = "YuriyPikachu.github.io"

    private var handler: YuriyHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Looper.perpare();

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiveMsgByMain()
        sendMsgByThread()

        Looper.loop()
    }

    private fun sendMsgByThread() {
        repeat(3) {
            Thread {
                while (true) {
                    val msg = Message.obtain()
                    msg.what = 1
                    synchronized(UUID::class.java) {
                        msg.obj =
                            currentThread().name + ",send message:" + UUID.randomUUID().toString()
                    }
                    Log.d(Tag, "send:$msg")
                    handler?.sendMessage(msg)
                    try {
                        sleep(1000)
                    } catch (e: InterruptedException) {
                        // TODO: handle exception
                        e.printStackTrace()
                    }
                }
            }.start()
        }
    }

    private fun receiveMsgByMain() {
        handler = object : YuriyHandler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.d(Tag, "received:$msg")
            }
        }
    }
}