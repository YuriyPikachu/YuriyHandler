package io.github.yuriypikachu.handler;

/**
 * @author YuLiang
 * update  2020/7/3
 * <a href="beiming@webuy.ai">Contact me</a>
 */

public class YuriyHandler {
    private MessageQueue mQueue;
    private Looper mLooper;


    //handler的初始化，在主线程当中完成。
    public YuriyHandler() {
        mLooper = Looper.myLooper();
        this.mQueue= mLooper.mQueue;
    }
    /**
     * 发送消息，压入队列
     * @paraam msg
     *
     */
    public void sendMessage(Message msg){
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    public void handleMessage(Message msg){

    }

    public void dispatchMessage(Message msg){
        handleMessage(msg);
    }
}

