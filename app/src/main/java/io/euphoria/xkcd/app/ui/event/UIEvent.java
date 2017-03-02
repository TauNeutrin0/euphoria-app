package io.euphoria.xkcd.app.ui.event;

import io.euphoria.xkcd.app.ui.RoomUI;

/** Created by Xyzzy on 2017-02-26. */

/* An abstract event generated by the UI */
public interface UIEvent {

    /* The room UI associated with this event */
    RoomUI getRoomUI();

}