package io.github.yuriypikachu.handler;

import org.jetbrains.annotations.NotNull;

/**
 * @author YuLiang
 * update  2020/7/3
 * <a href="YuriyPikachu.github.io">Contact me</a>
 */

public class Message {

    YuriyHandler target;

    public int what;
    public Object obj;
    private static Message sPool;
    Message next;
    /** @hide */
    public static final Object sPoolSync = new Object();

    @Override
    @NotNull
    public String toString(){
        return obj.toString();
    }

    public static Message obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                Message m = sPool;
                sPool = m.next;
                m.next = null;
                return m;
            }
        }
        return new Message();
    }

}