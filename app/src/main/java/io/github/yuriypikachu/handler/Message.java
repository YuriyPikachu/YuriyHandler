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

    @Override
    @NotNull
    public String toString(){
        return obj.toString();
    }

}