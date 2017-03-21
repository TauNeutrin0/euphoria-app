package io.euphoria.xkcd.app.control;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.euphoria.xkcd.app.connection.ConnectionManager;
import io.euphoria.xkcd.app.impl.connection.ConnectionManagerImpl;
import io.euphoria.xkcd.app.ui.event.UIEvent;

/** Created by Xyzzy on 2017-03-19. */

public class ConnectionService extends Service {
    public class CBinder extends Binder {
        public ConnectionService getService() {
            return ConnectionService.this;
        }
    }

    private final CBinder BINDER = new CBinder();
    private final Map<String, RoomEventQueue> roomEvents = new HashMap<>();
    private ConnectionManager mgr;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return BINDER;
    }

    @Override
    public void onCreate() {
        mgr = ConnectionManagerImpl.getInstance();
    }

    @Override
    public void onDestroy() {
        mgr.shutdown();
    }

    public void consume(List<EventWrapper<? extends UIEvent>> events) {
        for (EventWrapper<? extends UIEvent> evt : events) {
            String roomName = evt.getEvent().getRoomUI().getRoomName();
            RoomEventQueue queue = roomEvents.get(roomName);
            if (queue == null) {
                queue = new RoomEventQueue(roomName);
                roomEvents.put(roomName, queue);
            }
            queue.put(evt);
        }
    }
}
