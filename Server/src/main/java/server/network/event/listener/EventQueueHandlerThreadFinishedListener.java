package server.network.event.listener;

import server.network.event.EventQueueHandlerThread;

/**
 * Created by Justin on 03.12.2014.
 */
public interface EventQueueHandlerThreadFinishedListener {
    public void onFinishHandlerThread (EventQueueHandlerThread eventQueueHandlerThread);
}
