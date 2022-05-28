package com.comm.util.openlib.eventbus;

/**
 * @author : John
 * @date : 2018/7/11
 */
public class MessageEvent {
    private final String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
