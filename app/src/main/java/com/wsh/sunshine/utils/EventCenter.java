package com.wsh.sunshine.utils;

/**
 * EventCenter
 *
 * @author SuSong  su_song@gowild.cn
 * @version 15/9/30 下午5:38
 */
public class EventCenter<T> {

    /**
     * reserved data
     */
    private T data;

    /**
     * this code distinguish between different events
     */
    private int eventCode = -1;

    public EventCenter(int eventCode) {
        this(eventCode, null);
    }

    public EventCenter(int eventCode, T data) {
        this.eventCode = eventCode;
        this.data = data;
    }

    /**
     * get event code
     *
     * @return
     */
    public int getEventCode() {
        return this.eventCode;
    }

    /**
     * get event reserved data
     *
     * @return
     */
    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "EventCenter{" +
               "data=" + data +
               ", eventCode=" + eventCode +
               '}';
    }
}
