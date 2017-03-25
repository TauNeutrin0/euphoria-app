package io.euphoria.xkcd.app.control;

import io.euphoria.xkcd.app.ui.event.UIEvent;

/** Created by Xyzzy on 2017-03-25. */

public class RoomUIEventQueue extends EventQueue<UIEvent> {
    private long lastPoll;

    public RoomUIEventQueue(Runnable schedule) {
        super(schedule);
        lastPoll = System.currentTimeMillis();
    }

    @Override
    public void add(UIEvent e) {
        lastPoll = System.currentTimeMillis();
        super.add(e);
    }

    public boolean touchedSince(long time) {
        return (lastPoll >= time);
    }
}
