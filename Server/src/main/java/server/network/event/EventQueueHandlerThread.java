package server.network.event;

import server.network.event.listener.EventQueueHandlerThreadFinishedListener;

/**
 * Created by Justin on 03.12.2014.
 */
public class EventQueueHandlerThread implements Runnable {

    protected boolean isInterrupted = false;
    protected boolean hasFinished = false;
    protected EventQueueHandlerThreadFinishedListener eventQueueHandlerThreadFinishedListener = null;

    @Override
    public void run() {
        while (!this.isInterrupted) {
            //work in queue
        }

        this.hasFinished = true;

        if (this.eventQueueHandlerThreadFinishedListener != null) {
            this.eventQueueHandlerThreadFinishedListener.onFinishHandlerThread(this);
        }
    }

    public void setEventQueueHandlerThreadFinishedListener (EventQueueHandlerThreadFinishedListener eventQueueHandlerThreadFinishedListener) {
        this.eventQueueHandlerThreadFinishedListener = eventQueueHandlerThreadFinishedListener;
    }

    public synchronized void interrupt () {
        this.isInterrupted = true;
    }

    public synchronized boolean hasFinished () {
        return this.hasFinished;
    }

}
